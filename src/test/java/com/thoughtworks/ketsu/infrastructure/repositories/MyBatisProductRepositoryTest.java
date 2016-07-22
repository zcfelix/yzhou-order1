package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(DatabaseTestRunner.class)
public class MyBatisProductRepositoryTest {
    @Inject
    ProductRepository productRepository;

    @Test
    public void should_create_product_and_find_product_by_id() {
        Product product = productRepository.create(TestHelper.productMap(1, "apple", "delicious", 3.5));
        assertThat(product.getId(), is(1));
    }

    @Test
    public void should_list_all_products() {
        Product product = productRepository.create(TestHelper.productMap(1, "apple", "delicious", 3.5));
        List<Product> list = productRepository.findAll();
        assertThat(list.size(), is(1));
        assertThat(list.get(0).getName(), is("apple"));
    }

}
