package com.example.booking.repository;

import com.example.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByShowTimeIdAndSeatCodeAndStatusIn(Long showTimeId, String seatCode, List<String> statuses);

    List<Booking> findAllByStatusAndHoldExpireAtBefore(String status, LocalDateTime dateTime);

    List<Booking> findByStatusAndHoldExpireAtBefore(String status, LocalDateTime time);
}

