package com.example.payment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TicketDto {
    private String email;
    private String customerName;
    private String seatCode;
    private String showTime;
    private String movieName;
    private String cinemaName;
    private String ticketCode;
}
