package com.example.booking.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "seat")
@Data
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "seat_code",
            nullable = false,
            columnDefinition = "nvarchar(10)"
    )
    private String seatCode; // A1, A2...

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_time_id", nullable = false)
    private ShowTime showTime;
}
