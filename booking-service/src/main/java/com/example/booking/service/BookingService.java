package com.example.booking.service;

import com.example.booking.entity.Booking;
import com.example.booking.entity.ShowTime;
import com.example.booking.repository.BookingRepository;
import com.example.booking.repository.ShowTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ShowTimeRepository showTimeRepository;
    private final BookingRedisService bookingRedisService;

    @Transactional
    public Booking createBooking(Long showTimeId, String seatCode, String customerName, String email) {

        if (!bookingRedisService.holdSeat(showTimeId, seatCode)) {
            throw new SeatAlreadyHeldException("Ghế đã được giữ, vui lòng chọn ghế khác");
        }

        ShowTime showTime = showTimeRepository.findById(showTimeId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy suất chiếu"));

        Booking booking = new Booking();
        booking.setShowTime(showTime);
        booking.setSeatCode(seatCode);
        booking.setCustomerName(customerName);
        booking.setEmail(email);
        booking.setStatus("HOLD");
        booking.setCreatedAt(LocalDateTime.now());
        booking.setHoldExpireAt(LocalDateTime.now().plusMinutes(10));

        return bookingRepository.save(booking);
    }

    public static class SeatAlreadyHeldException extends RuntimeException {
        public SeatAlreadyHeldException(String message) {
            super(message);
        }
    }

    @Scheduled(fixedRateString = "${booking.release-check-rate-ms:60000}") // kiểm tra mỗi phút
    @Transactional
    public void releaseExpiredBookings() {
        LocalDateTime now = LocalDateTime.now();

        // Lấy tất cả booking đang HOLD mà đã hết thời gian hold
        List<Booking> expired = bookingRepository.findByStatusAndHoldExpireAtBefore("HOLD", now);

        for (Booking booking : expired) {
            // Xóa key Redis
            bookingRedisService.releaseSeat(booking.getShowTime().getId(), booking.getSeatCode());

            // Update trạng thái trong DB
            booking.setStatus("EXPIRED");
            bookingRepository.save(booking);
        }
    }
}