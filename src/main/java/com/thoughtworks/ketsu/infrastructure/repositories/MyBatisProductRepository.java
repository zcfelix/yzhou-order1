package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;

import javax.inject.Inject;
import java.util.Map;

public class MyBatisProductRepository implements ProductRepository{
    @Inject
    ProductMapper productMapper;

    @Override
    public void create(Map<String, Object> info) {
        productMapper.create(info);
    }

    @Override
    public Product findById(int id) {
        return productMapper.findById(id);
    }
}
