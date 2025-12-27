package com.example.notification.controller;

import com.example.notification.dto.TicketDto;
import com.example.notification.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-mail")
public class TestMailController {

    private final EmailService emailService;

    public TestMailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public String sendMail() throws Exception {

        TicketDto ticket = new TicketDto();
        ticket.setMovieName("Avengers: Endgame");
        ticket.setCinemaName("CGV Vincom");
        ticket.setShowTime("20:00 - 25/12/2025");
        ticket.setSeatCode("A1");
        ticket.setTicketCode("BK-20251225-0001");
        ticket.setCustomerName("Nguyễn Văn A");
        ticket.setEmail("hunghd0606@gmail.com");
        ticket.setStatus("PAID");

        emailService.sendTicketEmail(ticket);
        return "MAIL SENT";
    }
}
