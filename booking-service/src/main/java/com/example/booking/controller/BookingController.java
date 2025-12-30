package com.example.booking.controller;

import com.example.booking.dto.BookingRequest;
import com.example.booking.entity.Booking;
import com.example.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {

        Booking booking = bookingService.createBooking(
                request.getShowTimeId(),
                request.getSeatCode(),
                request.getCustomerName(),
                request.getEmail()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("bookingId", booking.getId());
        response.put("status", booking.getStatus());

        return ResponseEntity.ok(response);
    }
}
