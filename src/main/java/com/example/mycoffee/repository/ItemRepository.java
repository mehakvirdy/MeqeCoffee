package com.example.mycoffee.repository;
import com.example.mycoffee.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}