package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(DatabaseTestRunner.class)
public class MyBatisProductRepositoryTest {
    @Inject
    ProductRepository productRepository;

    @Test
    public void should_create_product_and_find_product_by_id() {
        Map<String, Object> productInfo = new HashMap() {{
            put("id", 1);
            put("name", "mac");
            put("desc", "computer");
            put("price", 13000.0);
        }};

        productRepository.create(productInfo);
        Product product = productRepository.findById(1);
        assertThat(product.getId(), is(1));
    }

}
