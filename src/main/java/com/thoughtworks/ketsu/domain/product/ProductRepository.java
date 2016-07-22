package com.thoughtworks.ketsu.domain.product;

import java.util.List;
import java.util.Map;

public interface ProductRepository {
    Product create(Map<String, Object> info);

    List<Product> findAll();

    Product findById(int id);
}