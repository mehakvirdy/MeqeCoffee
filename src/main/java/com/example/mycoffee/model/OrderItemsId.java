package com.example.mycoffee.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OrderItemsId implements Serializable {
    private int customerId;
    private LocalDateTime dateTime;

    // Default constructor
    public OrderItemsId() {}

    // Constructor, equals, and hashCode
    public OrderItemsId(int customer, LocalDateTime dateTime) {
        this.customerId = customer;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemsId that = (OrderItemsId) o;
        return customerId == that.customerId && dateTime == that.dateTime;
    }

    @Override
    public int hashCode() {
        return 31 * customerId + dateTime.hashCode();
    }
}