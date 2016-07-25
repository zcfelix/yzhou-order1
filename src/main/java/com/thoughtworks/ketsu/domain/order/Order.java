package com.thoughtworks.ketsu.domain.order;

import com.thoughtworks.ketsu.domain.orderitem.OrderItem;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.*;

public class Order implements Record {
    private int id;
    private int userId;
    private String name;
    private String address;
    private String phone;
    private double totalPrice;
    private Date time;
    private List<OrderItem> items;

    public Order(int userId, int id) {
        this.userId = userId;
        this.id = id;
    }

    public Order() {

    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Date getTime() {
        return time;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object> () {{
            put("url", routes.orderUrl(Order.this));
            put("name", name);
            put("address", address);
            put("phone", phone);
            put("total_price", totalPrice);
            put("created_at", time);
            //List<Map<String, Object>> orderItems = new ArrayList<>();
        }};
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("url", routes.orderUrl(Order.this));
            put("name", name);
            put("address", address);
            put("phone", phone);
            put("total_price", totalPrice);
            put("created_at", time);
        }};
    }
}
