package com.thoughtworks.ketsu.domain.order;

import com.thoughtworks.ketsu.domain.orderitem.OrderItem;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.*;

import static com.sun.deploy.perf.DeployPerfUtil.put;

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
        Map<String, Object> ret = new HashMap<String, Object>() {{
            put("uri", routes.orderUrl(Order.this));
            put("name", name);
            put("address", address);
            put("phone", phone);
            put("total_price", totalPrice);
            put("created_at", time);
        }};
        List<Map<String, Object>> orderItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < items.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("product_id", items.get(i).getProductId());
            map.put("quantity", items.get(i).getQuantity());
            map.put("amount", items.get(i).getAmount());
            orderItems.add(map);
        }
        ret.put("order_items", orderItems);
        return ret;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }
}
