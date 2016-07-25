package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(DatabaseTestRunner.class)
public class MyBatisOrderRepositoryTest {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Test
    public void should_create_order_for_user() {
        User user = userRepository.createUser(TestHelper.userMap("felix"));
        Product product = productRepository.create(TestHelper.productMap("apple", "delicious", 2.35));
        Order order = user.createOrder(TestHelper.orderMap("kitty", product.getId()));
        assertThat(order.getName(), is("kitty"));
        assertThat(order.getPhone(), is("15184452287"));
    }
}
