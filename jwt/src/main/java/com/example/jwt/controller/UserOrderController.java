package com.example.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.model.Order;
import com.example.jwt.model.User;
import com.example.jwt.service.OrderService;
import com.example.jwt.service.UserService;
@RestController
@RequestMapping("/user/orders")
@PreAuthorize("hasRole('USER')")
public class UserOrderController {
     @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService; // Thêm service để lấy User entity từ DB

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order, Authentication auth) {
        String username = auth.getName(); // Lấy username từ token
        User user = userService.findByUsername(username); // Tìm User từ DB

        order.setUser(user); // Gán user thực thể vào đơn hàng
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getMyOrders(Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(orderService.getUserOrders(username));
    }
}
