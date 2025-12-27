package com.example.notification.service;

import com.example.notification.dto.TicketDto;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender,
                        TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendTicketEmail(TicketDto ticket) throws MessagingException {

        Context context = new Context();
        context.setVariable("ticket", ticket);

        String html = templateEngine.process("ticket", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(ticket.getEmail());
        helper.setSubject("🎬 Vé xem phim - " + ticket.getMovieName());
        helper.setText(html, true);

        mailSender.send(message);
    }
}
