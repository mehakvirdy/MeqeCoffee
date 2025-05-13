package com.example.mycoffee.controller;
import com.example.mycoffee.model.OrderItems;
import com.example.mycoffee.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orderitems")
public class OrderItemController {
    @Autowired
    private OrderItemsRepository orderItemRepository;

    @GetMapping
    public List<OrderItems> getAllOrders() {
        return orderItemRepository.findAll();
    }

    @PostMapping
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public OrderItems createOrder(@RequestBody OrderItems order) {
        return orderItemRepository.save(order);
    }

    @PutMapping("/{id}")
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public OrderItems updateCustomer(@PathVariable int id, @RequestBody OrderItems order) {
        // check if the order exists
        OrderItems existingOrder = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        // update the fields of the existing order
        // assuming order is the new order information
        existingOrder.setItem(order.getItem());
        existingOrder.setQuantity(order.getQuantity());


        // save the updated order back to the repository
        return orderItemRepository.save(existingOrder);
    }

    @DeleteMapping("/{id}")
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void deleteOrder(@PathVariable int id) {
        orderItemRepository.deleteById(id);
    }
}