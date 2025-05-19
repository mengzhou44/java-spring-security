package com.mengzhou.controllers;


import com.mengzhou.entities.Order;
import com.mengzhou.repositories.OrderRepository;
import com.mengzhou.security.AuthContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
   private final AuthContext authContext;
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository, AuthContext authContext) {
        this.orderRepository = orderRepository;
        this.authContext = authContext;
    }

    @GetMapping("/users/{userId}/orders")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        System.out.println(authContext.getName());
        System.out.println(authContext.getRole());
        return orderRepository.findByUserId(userId);
    }
}
