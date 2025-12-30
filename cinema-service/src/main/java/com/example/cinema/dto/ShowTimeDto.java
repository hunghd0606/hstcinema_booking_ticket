package com.example.cinema.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ShowTimeDto {
    private Long id;
    private String startTime;

    private Long movieId;
    private String movieTitle;
    private Integer duration;

    private Long cinemaId;
    private String cinemaName;
}
