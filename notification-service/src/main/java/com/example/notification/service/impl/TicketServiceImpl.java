package com.example.notification.service.impl;

import com.example.notification.dto.TicketDto;
import com.example.notification.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Override
    public TicketDto getTicketInfo(Long bookingId) {
        TicketDto dto = new TicketDto();
        dto.setMovieName("Avengers: Endgame");
        dto.setCinemaName("CGV Vincom");
        dto.setShowTime("20:00 - 25/12/2025");
        dto.setSeatCode("A1");
        dto.setTicketCode("BK-20251225-0001");
        dto.setCustomerName("Nguyễn Văn A");
        dto.setEmail("user@gmail.com");
        dto.setStatus("PAID");
        return dto;
    }

    @Override
    public void sendTicketEmail(TicketDto ticketDto) {
        System.out.println("Send email to " + ticketDto.getEmail());
    }
}
