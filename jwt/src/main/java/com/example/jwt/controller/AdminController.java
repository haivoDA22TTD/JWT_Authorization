package com.example.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.model.User;
import com.example.jwt.service.UserService;
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard")
     public ResponseEntity<String> getDashboard() {
        return ResponseEntity.ok("Welcome to admin dashboard");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/users")
    public ResponseEntity <List <User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
