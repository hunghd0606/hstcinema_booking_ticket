package com.example.booking.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer duration;
}