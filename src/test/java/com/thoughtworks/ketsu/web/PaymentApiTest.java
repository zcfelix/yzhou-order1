package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(ApiTestRunner.class)
public class PaymentApiTest extends ApiSupport{
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;


    private User user;
    private Product product;
    private Order order;
    private String paymentBaseUrl;

    @Override
    @Before
    public void setUp() throws Exception{
        super.setUp();
        user = userRepository.createUser(TestHelper.userMap("felix"));
        product = productRepository.create(TestHelper.productMap("apple", "delicious", 3.14));
        order = user.createOrder(TestHelper.orderMap("kitty", product.getId()));
        paymentBaseUrl = "/users/" + user.getId() + "/orders/" + order.getId() + "/payment";
    }

    @Test
    public void should_return_201_when_create_payment() {
        final Response POST = post(paymentBaseUrl, TestHelper.paymentMap());
        assertThat(POST.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
    }
}
