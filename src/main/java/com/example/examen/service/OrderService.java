package com.example.examen.service;

import com.example.examen.model.Order;
import com.example.examen.model.User;
import com.example.examen.repository.OrderRepository;
import com.example.examen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

     public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

     public List<Order> getUserOrders(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findByUserId(user.getId());
    }

    public Order createOrder(Order order, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);
        return orderRepository.save(order);
    }
}