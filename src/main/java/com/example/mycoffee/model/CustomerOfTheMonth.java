package com.example.mycoffee.model;

public class CustomerOfTheMonth {

    private int customerId;
    private String customerName;
    private double totalSpent;

    // Constructor, getters, and setters

    public CustomerOfTheMonth(int customerId, String customerName, double totalSpent) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalSpent = totalSpent;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }
}
