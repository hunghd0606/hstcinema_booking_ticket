package com.example.booking.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", columnDefinition = "nvarchar(255)", nullable = false)
    private String customerName;

    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String email;

    @Column(name = "seat_code", columnDefinition = "nvarchar(10)", nullable = false)
    private String seatCode;

    @ManyToOne
    @JoinColumn(name = "show_time_id", nullable = false)
    private ShowTime showTime;

    @Column(columnDefinition = "nvarchar(50)", nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime holdExpireAt;
}
