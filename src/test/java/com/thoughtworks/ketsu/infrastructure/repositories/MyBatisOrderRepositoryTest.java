package com.thoughtworks.ketsu.infrastructure.repositories;

import com.sun.javafx.collections.MappingChange;
import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.orderitem.OrderItem;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(DatabaseTestRunner.class)
public class MyBatisOrderRepositoryTest {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    private User user;
    private Product product;

    @Before
    public void setUp() {
        user = userRepository.createUser(TestHelper.userMap("felix"));
        product = productRepository.create(TestHelper.productMap("duck-head", "delicious", 12.5));
    }

    @Test
    public void should_create_order_for_user() {
        //User user = userRepository.createUser(TestHelper.userMap("felix"));
        //Product product = productRepository.create(TestHelper.productMap("apple", "delicious", 2.35));
        Order order = user.createOrder(TestHelper.orderMap("kitty", product.getId()));
        assertThat(order.getName(), is("kitty"));
        assertThat(order.getPhone(), is("15184452287"));
    }

    @Test
    public void should_list_orders_for_user() {
        Order order = user.createOrder(TestHelper.orderMap("kitty", product.getId()));
        List<Order> list = user.listOrder();
        assertThat(list.size(), is(1));
        assertThat(list.get(0).getName(), is("kitty"));
    }
}
