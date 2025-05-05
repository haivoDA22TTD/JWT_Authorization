package com.example.jwt.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwt.config.JwtService;
import com.example.jwt.dto.AuthRequest;
import com.example.jwt.dto.AuthResponse;
import com.example.jwt.dto.RegisterRequest;
import com.example.jwt.entity.Role;
import com.example.jwt.entity.User;
import com.example.jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse authenticate(AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()
                    )
            );

            // Xác thực thành công, tìm user và sinh JWT
            User user = userRepository.findByUsername(request.getUsername())
                                      .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtService.generateToken(user);
            return new AuthResponse(token);

        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password");
        }
    }

   
    
}
