package com.thoughtworks.ketsu.domain.orderitem;

public class OrderItem {
    private int productId;
    private int quantity;
    private double amount;

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }
}
