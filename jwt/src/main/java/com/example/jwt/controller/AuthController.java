package com.example.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.dto.AuthRequest;
import com.example.jwt.dto.AuthResponse;
import com.example.jwt.dto.RegisterRequest;
import com.example.jwt.service.AuthService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
     private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
