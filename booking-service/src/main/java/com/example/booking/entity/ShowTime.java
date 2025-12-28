package com.example.booking.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "show_time")
@Data
public class ShowTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(
            name = "start_time",
            nullable = false,
            columnDefinition = "datetime2"
    )
    private LocalDateTime startTime;
}
