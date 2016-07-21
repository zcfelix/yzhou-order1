package com.thoughtworks.ketsu.domain.product;

public class Product {
    private int id;
    private String name;
    private String desc;
    private double price;

    public Product() {
    }

    public Product(int id, String name, String desc, double price) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public double getPrice() {
        return price;
    }
}
