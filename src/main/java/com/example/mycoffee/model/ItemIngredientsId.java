package com.example.mycoffee.model;
import java.io.Serializable;

public class ItemIngredientsId implements Serializable {
    private Item item;
    private Inventory ingredient;

    // Default constructor
    public ItemIngredientsId() {}

    // Constructor, equals, and hashCode
    public ItemIngredientsId(Item item, Inventory ingredient) {
        this.item = item;
        this.ingredient = ingredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemIngredientsId that = (ItemIngredientsId) o;
        return item == that.item && ingredient == that.ingredient;
    }

    @Override
    public int hashCode() {
        return 31 * item.getItemId() + ingredient.getIngredientId();
    }
}