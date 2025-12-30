package com.example.cinema.repository;

import com.example.cinema.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {

    List<ShowTime> findByCinemaId(Long cinemaId);

    List<ShowTime> findByMovieId(Long movieId);

    List<ShowTime> findByCinemaIdAndMovieId(Long cinemaId, Long movieId);

}

