package com.example.mycoffee.model;
import com.example.mycoffee.model.Inventory;
import com.example.mycoffee.model.Item;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@IdClass(ItemIngredientsId.class)
public class ItemIngredients {
    @Id
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Id
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Inventory ingredient;

    private BigDecimal quantity;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Inventory getIngredient() {
        return ingredient;
    }

    public void setIngredient(Inventory ingredient) {
        this.ingredient = ingredient;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}