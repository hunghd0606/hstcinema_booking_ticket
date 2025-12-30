package com.example.cinema.controller;

import com.example.cinema.dto.ShowTimeDto;
import com.example.cinema.entity.ShowTime;
import com.example.cinema.mapper.ShowTimeMapper;
import com.example.cinema.repository.ShowTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/showtimes")
@RequiredArgsConstructor
public class ShowTimeController {

    private final ShowTimeRepository showTimeRepository;

    @GetMapping("/cinema/{cinemaId}")
    public List<ShowTimeDto> getShowTimesByCinema(@PathVariable Long cinemaId) {
        return showTimeRepository.findByCinemaId(cinemaId)
                .stream()
                .map(ShowTimeMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/movie/{movieId}")
    public List<ShowTime> getShowTimesByMovie(@PathVariable Long movieId) {
        return showTimeRepository.findByMovieId(movieId);
    }

}
