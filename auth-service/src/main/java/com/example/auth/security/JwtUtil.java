package com.example.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "ticket-booking-secret-key-123456789012345"; // >=32 ký tự
    private static final long EXPIRATION = 1000 * 60 * 60; // 1 giờ

    public static String generateToken(String email) {
        Key hmacKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8),
                SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(hmacKey)
                .compact();
    }
}
