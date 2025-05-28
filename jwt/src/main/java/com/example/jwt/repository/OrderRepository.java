package com.example.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwt.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByUserUsername(String username);
}
