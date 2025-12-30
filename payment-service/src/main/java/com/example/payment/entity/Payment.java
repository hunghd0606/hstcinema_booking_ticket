package com.example.payment.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_id", nullable = false)
    private Long bookingId; // liên kết đến booking

    @Column(nullable = false)
    private BigDecimal amount;// số tiền thanh toán

    @Column(length = 20, columnDefinition = "nvarchar(20)", nullable = false)
    private String status; // PENDING, SUCCESS, FAILED

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
