package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.domain.AssertionConcern;
import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.inject.Inject;
import java.util.HashMap;
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
        info.put("total_price", 0);
        orderMapper.save(info);
        return orderMapper.findById(Integer.valueOf(info.get("id").toString()));
    }
}
