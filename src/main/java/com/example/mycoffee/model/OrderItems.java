package com.example.mycoffee.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@IdClass(OrderItemsId.class)
@Table(name = "order_items")
public class OrderItems {
    @Id
    @JoinColumn(name = "customer_id")
    private int customerId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int quantity;

    @Id
    private LocalDateTime dateTime;

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerID) {
        this.customerId = customerID;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}