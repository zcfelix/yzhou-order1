package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.domain.AssertionConcern;
import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class User implements Record {

    @Inject
    OrderMapper orderMapper;
    @Inject
    ProductMapper productMapper;

    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("id", id);
            put("uri", routes.userUrl(User.this));
            put("name", name);
        }};
    }

    public Order createOrder(Map<String, Object> info) {
        info.put("user_id", id);
        double price = 0.0;
        List<Map<String, Object>> itemlist = (List<Map<String, Object>>)info.get("order_items");
        for (int i = 0; i < itemlist.size(); i++) {
            Product product = productMapper.findById(Integer.valueOf(itemlist.get(i).get("product_id").toString()));
            double amount = product.getPrice() * Integer.valueOf(itemlist.get(i).get("quantity").toString());
            price += amount;
            itemlist.get(i).put("amount", amount);
        }
        info.put("total_price", price);
        orderMapper.save(info);
        return orderMapper.findById(Integer.valueOf(info.get("id").toString()));
    }
}
