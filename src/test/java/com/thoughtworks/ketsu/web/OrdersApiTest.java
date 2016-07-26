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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

import java.util.*;
import java.util.regex.Pattern;

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
    public void should_return_201_and_location_when_create_a_valid_order() {
        final Response POST = post(orderBaseUrl, TestHelper.orderMap("felix", product.getId()));
        assertThat(POST.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
        assertThat(Pattern.matches(".*?/users/[0-9-]*/orders/[0-9-]*", POST.getLocation().toASCIIString()), is(true));
    }

    @Test
    public void should_return_400_when_create_an_invalid_order() {
        final Response POST = post(orderBaseUrl, TestHelper.orderMap("", product.getId()));
        assertThat(POST.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
        final List<Map<String, Object>> errorInfo = POST.readEntity(List.class);
        assertThat(errorInfo.size(), is(1));
    }

    @Test
    public void should_return_200_when_get_an_order() {
        Order order = user.createOrder(TestHelper.orderMap("felix", product.getId()));
        final Response GET = get(orderBaseUrl + "/" + order.getId());
        assertThat(GET.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
    }

    @Test
    public void should_return_details_when_get_an_order() {
        Order order = user.createOrder(TestHelper.orderMap("felix", product.getId()));
        final Response GET = get(orderBaseUrl + "/" + order.getId());
        assertThat(GET.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
        final Map<String, Object> ret = GET.readEntity(Map.class);
        assertThat(ret.get("uri"), is("/users/" + user.getId() + "/orders/" + order.getId()));
        List<Map<String, Object>> items = (List<Map<String, Object>>)ret.get("order_items");
        assertThat(items.size(), is(1));
        assertThat(items.get(0).get("product_id").toString(), is(String.valueOf(product.getId())));
    }


}
