package com.example.notification.dto;

import lombok.Data;

@Data
public class TicketDto {
    private String movieName;
    private String cinemaName;
    private String showTime;
    private String seatCode;
    private String ticketCode;
    private String customerName;
    private String email;
    private String status;
}
