package com.example.notification.service;

import com.example.notification.dto.TicketDto;

public interface TicketService {
    TicketDto getTicketInfo(Long bookingId);
    void sendTicketEmail(TicketDto ticketDto);
}