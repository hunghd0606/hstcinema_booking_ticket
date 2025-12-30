package com.example.booking.service;

import com.example.booking.entity.Booking;
import com.example.booking.entity.ShowTime;
import com.example.booking.repository.BookingRepository;
import com.example.booking.repository.ShowTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class SeatReleaseScheduler {

    private final BookingRepository bookingRepository;

    @Scheduled(fixedRate = 60000) // chạy mỗi phút
    public void releaseExpiredSeats() {
        LocalDateTime now = LocalDateTime.now();

        List<Booking> expiredBookings = bookingRepository.findAllByStatusAndHoldExpireAtBefore("HOLD", now);

        for (Booking booking : expiredBookings) {
            booking.setStatus("AVAILABLE");
            booking.setHoldExpireAt(null);
            bookingRepository.save(booking);
        }
    }
}
