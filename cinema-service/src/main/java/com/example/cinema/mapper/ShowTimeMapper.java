package com.example.cinema.mapper;

import com.example.cinema.dto.ShowTimeDto;
import com.example.cinema.entity.ShowTime;

public class ShowTimeMapper {

    public static ShowTimeDto toDto(ShowTime st) {
        ShowTimeDto dto = new ShowTimeDto();

        dto.setId(st.getId());
        dto.setStartTime(st.getStartTime().toString());

        dto.setMovieId(st.getMovie().getId());
        dto.setMovieTitle(st.getMovie().getTitle());
        dto.setDuration(st.getMovie().getDuration());

        dto.setCinemaId(st.getCinema().getId());
        dto.setCinemaName(st.getCinema().getName());

        return dto;
    }
}
