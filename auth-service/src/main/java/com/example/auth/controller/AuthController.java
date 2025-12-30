package com.example.auth.controller;

import com.example.auth.dto.LoginRequest;
import com.example.auth.dto.RegisterRequest;
import com.example.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest req) {
        authService.register(req);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }
}
