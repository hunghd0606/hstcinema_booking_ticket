package com.example.payment.controller;

import com.example.payment.entity.Payment;
import com.example.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> createPayment(
            @RequestParam Long bookingId,
            @RequestParam Double amount
    ) {
        Payment payment = paymentService.pay(bookingId, amount);
        Map<String, Object> resp = new HashMap<>();
        resp.put("paymentId", payment.getId());
        resp.put("status", payment.getStatus());

        return ResponseEntity.ok(payment);
    }
}
