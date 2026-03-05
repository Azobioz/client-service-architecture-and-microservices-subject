package com.service.order.controller;

import com.service.order.dto.UserDTO;
import com.service.order.model.Order;
import com.service.order.repository.OrderRepository;
import com.service.order.service.UserClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository repository;
    private final UserClient userClient;

    public OrderController(OrderRepository repository, UserClient userClient) {
        this.repository = repository;
        this.userClient = userClient;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getOrder(@PathVariable Long id) {

        Order order = repository.findById(id).orElseThrow();

        UserDTO user = userClient.getUser(order.getUserId());

        Map<String, Object> response = new HashMap<>();
        response.put("order", order);
        response.put("user", user);

        return response;
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        userClient.getUser(order.getUserId());
        return repository.save(order);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @RequestBody Order updatedOrder) {

        Order order = repository.findById(id).orElseThrow();

        // проверяем существование пользователя
        userClient.getUser(updatedOrder.getUserId());

        order.setUserId(updatedOrder.getUserId());
        order.setProduct(updatedOrder.getProduct());
        order.setQuantity(updatedOrder.getQuantity());

        return repository.save(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
