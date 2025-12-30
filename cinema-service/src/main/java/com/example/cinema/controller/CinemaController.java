package com.example.cinema.controller;

import com.example.cinema.dto.CinemaDto;
import com.example.cinema.repository.CinemaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cinemas")
public class CinemaController {

    private final CinemaRepository cinemaRepository;

    public CinemaController(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @GetMapping("")
    public List<CinemaDto> getAllCinemas() {
        return cinemaRepository.findAll()
                .stream()
                .map(c -> {
                    CinemaDto dto = new CinemaDto();
                    dto.setId(c.getId());
                    dto.setName(c.getName());
                    dto.setAddress(c.getAddress());
                    return dto;
                })
                .collect(Collectors.toList());

    }

}
