package com.example.auth.service;

import com.example.auth.dto.LoginRequest;
import com.example.auth.dto.RegisterRequest;
import com.example.auth.entity.User;
import com.example.auth.repository.UserRepository;
import com.example.auth.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public void register(RegisterRequest req) {
        User u = new User();
        u.setEmail(req.email);
        u.setPassword(req.password); // demo, chưa hash
        u.setRole("USER");
        userRepository.save(u);
    }

    public String login(LoginRequest req) {
        User u = userRepository.findByEmail(req.email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!u.getPassword().equals(req.password)) {
            throw new RuntimeException("Invalid password");
        }

        return JwtUtil.generateToken(u.getEmail());
    }
}
