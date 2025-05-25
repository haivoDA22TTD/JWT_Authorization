package com.example.jwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jwt.model.User;
import com.example.jwt.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User>getAllUsers(){
        return userRepository.findAll();
    }
}
