package com.example.mycoffee.controller;
import com.example.mycoffee.model.Customer;
import com.example.mycoffee.model.Order;
import com.example.mycoffee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PutMapping("/{id}")
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Order updateCustomer(@PathVariable int id, @RequestBody Order order) {
        // check if the order exists
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        // update the fields of the existing order
        // assuming order is the new order information
        existingOrder.setCustomer(order.getCustomer());
        existingOrder.setPaymentMethod(order.getPaymentMethod());
        existingOrder.setTotalPrice(order.getTotalPrice());
        existingOrder.setDate(order.getDate());


        // save the updated order back to the repository
        return orderRepository.save(existingOrder);
    }

    @DeleteMapping("/{id}")
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void deleteOrder(@PathVariable int id) {
        orderRepository.deleteById(id);
    }
}