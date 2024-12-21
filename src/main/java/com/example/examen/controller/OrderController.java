package com.example.examen.controller;

import com.example.examen.model.Order;
import com.example.examen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    public Order createOrder(@RequestBody Order order, Principal principal) {
        return orderService.createOrder(order, principal.getName());
    }

    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }
}