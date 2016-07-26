package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.factory.ObjectFactory;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
    public void should_return_201_when_create_a_valid_payment() {
        final Response POST = post(paymentBaseUrl, TestHelper.paymentMap());
        assertThat(POST.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
        assertThat(Pattern.matches(".*?/users/[0-9-]*/orders/[0-9-]*/payment", POST.getLocation().toASCIIString()), is(true));
    }

    @Test
    public void should_return_400_when_create_an_invalid_payment() {
        final Response POST = post(paymentBaseUrl, new HashMap<String, Object>());
        assertThat(POST.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
        final List<Map<String, Object>> errorInfo = POST.readEntity(List.class);
        assertThat(errorInfo.size(), is(2));
    }

    @Test
    public void should_return_200_when_get_a_payment() {
        final Response GET = get(paymentBaseUrl);
        assertThat(GET.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
    }
}
