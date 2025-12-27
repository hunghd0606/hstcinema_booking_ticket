package com.example.notification.controller;

import com.example.notification.dto.TicketDto;
import com.example.notification.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/1")
    public String viewTicket1(Model model) {
        model.addAttribute("movieName", "Avengers: Endgame");
        model.addAttribute("cinemaName", "CGV Vincom");
        model.addAttribute("showTime", "20:00 - 25/12/2025");
        model.addAttribute("seatCode", "A1");
        model.addAttribute("ticketCode", "BK-20251225-0001");
        model.addAttribute("customerName", "Nguyễn Văn A");
        model.addAttribute("email", "user@gmail.com");
        model.addAttribute("status", "PAID");
        return "ticket";
    }

    @GetMapping("/{bookingId}")
    public String viewTicket(@PathVariable Long bookingId, Model model) {
        TicketDto ticket = ticketService.getTicketInfo(bookingId);
        model.addAttribute("ticket", ticket);
        return "ticket";
    }


}
