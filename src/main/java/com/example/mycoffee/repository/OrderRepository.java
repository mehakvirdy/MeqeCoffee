package com.example.mycoffee.repository;
import com.example.mycoffee.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}