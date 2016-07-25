package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.orderitem.OrderItem;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.MyBatisProductRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.*;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class OrdersApiTest extends ApiSupport {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    private User user;
    private Product product;
    private String orderBaseUrl;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        user = userRepository.createUser(TestHelper.userMap("felix"));
        product = productRepository.create(TestHelper.productMap("apple", "delicious", 2.5));
        orderBaseUrl = "/users/" + user.getId() + "/orders";
    }

    @Test
    public void should_return_201_when_create_an_order() {
        final Response POST = post(orderBaseUrl, TestHelper.orderMap("felix", product.getId()));
        assertThat(POST.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
    }

}
