package com.example.jwt.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jwt.model.Order;
import com.example.jwt.repository.OrderRepository;
@Service
public class OrderService {
     @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(String username) {
        return orderRepository.findByUserUsername(username);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order approveOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus("APPROVED");
        return orderRepository.save(order);
    }

    public Order rejectOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus("REJECTED");
        return orderRepository.save(order);
    }
}
