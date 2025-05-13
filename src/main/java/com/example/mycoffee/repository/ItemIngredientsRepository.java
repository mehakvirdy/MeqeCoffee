package com.example.mycoffee.repository;
import com.example.mycoffee.model.ItemIngredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemIngredientsRepository extends JpaRepository<ItemIngredients, Integer> {
}