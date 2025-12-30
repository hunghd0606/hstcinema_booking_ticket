package com.example.payment.service;

import com.example.payment.dto.TicketDto;
import com.example.payment.entity.*;
import com.example.payment.exception.GlobalExceptionHandler;
import com.example.payment.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final ShowTimeRepository showTimeRepository;
    private final CinemaRepository cinemaRepository;
    private final RabbitTemplate rabbitTemplate;

    @Transactional
    public Payment pay(Long bookingId, Double amount) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new GlobalExceptionHandler.BookingException("Booking không tồn tại"));

        if (!"HOLD".equals(booking.getStatus())) {
            throw new GlobalExceptionHandler.BookingException("Booking không ở trạng thái chờ thanh toán");
        }

        Payment payment = new Payment();
        payment.setBookingId(booking.getId());
        payment.setAmount(BigDecimal.valueOf(amount));
        payment.setStatus("SUCCESS"); // giả lập luôn thành công
        payment.setCreatedAt(LocalDateTime.now());
        paymentRepository.save(payment);

        // 2️⃣ Cập nhật Booking sang BOOKED
        booking.setStatus("BOOKED");
        bookingRepository.save(booking);

        // 3️⃣ Bắn message queue cho Notification Service
        Map<String, Object> message = new HashMap<>();
        message.put("bookingId", booking.getId());
        message.put("customerEmail", booking.getEmail());
        message.put("amount", amount);

        // Lấy thông tin ShowTime từ DB
        ShowTime showTime = showTimeRepository.findById(booking.getShowTime().getId())
                .orElseThrow(() -> new RuntimeException("ShowTime not found"));
        Movie movie = showTime.getMovie();
        Cinema cinema = showTime.getCinema(); // Lấy trực tiếp

        TicketDto ticketDto = new TicketDto();
        ticketDto.setEmail(booking.getEmail());
        ticketDto.setCustomerName(booking.getCustomerName());
        ticketDto.setSeatCode(booking.getSeatCode());
        ticketDto.setShowTime(showTime.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        ticketDto.setMovieName(movie.getTitle());
        ticketDto.setCinemaName(cinema.getName()); // tên rạp đúng
        ticketDto.setTicketCode("BK-" + booking.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-" + String.format("%04d", booking.getId()));

        rabbitTemplate.convertAndSend(
                "notification-exchange",
                "booking.paid",
                ticketDto
        );

        return payment;
    }

}
