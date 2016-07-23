package com.thoughtworks.ketsu.domain.product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepository {
    Product create(Map<String, Object> info);

    List<Product> findAll();

    Optional<Product> findById(int id);
}