package com.example.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jwt.model.User;
import com.example.jwt.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    public void deleteUserById(Long id) {
    if (userRepository.existsById(id)) {
        userRepository.deleteById(id);
    } else {
        throw new RuntimeException("User không tồn tại với ID: " + id);
    }
}


}
