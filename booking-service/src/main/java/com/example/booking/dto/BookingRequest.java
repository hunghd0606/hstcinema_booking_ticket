package com.example.booking.dto;

import lombok.Data;

@Data
public class BookingRequest {
    private Long showTimeId;
    private String seatCode;
    private String customerName;
    private String email;
}