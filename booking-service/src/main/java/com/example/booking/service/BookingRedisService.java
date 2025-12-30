package com.example.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class BookingRedisService {

    @Qualifier("bookingRedisTemplate")
    private final RedisTemplate<String, String> redisTemplate;


    private String buildKey(Long showTimeId, String seatCode) {
        return "seat:hold:" + showTimeId + ":" + seatCode;
    }

    public boolean holdSeat(Long showTimeId, String seatCode) {
        String key = buildKey(showTimeId, seatCode);

        Boolean success = redisTemplate.opsForValue().setIfAbsent(
                key,
                "HOLD",
                Duration.ofMinutes(10)
        );

        return Boolean.TRUE.equals(success);
    }

    public void releaseSeat(Long showTimeId, String seatCode) {
        String key = buildKey(showTimeId, seatCode);
        redisTemplate.delete(key);
    }

}