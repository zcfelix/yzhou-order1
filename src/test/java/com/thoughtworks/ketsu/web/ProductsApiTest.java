package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class ProductsApiTest extends ApiSupport{

    @Inject
    ProductRepository productRepository;
    Routes routes;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void should_return_201_and_location_when_create_valid_product() {
        final Response POST = post("products", TestHelper.productMap(1, "pen", "writing", 1299.99));
        assertThat(POST.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
        assertThat(Pattern.matches(".*?/products/[0-9-]*", POST.getLocation().toASCIIString()), is(true));
    }

    @Test
    public void should_return_400_when_create_invalid_product() {
        final Response POST = post("products", TestHelper.productMap(2, "mac", "", 9300.00));
        assertThat(POST.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }

    @Test
    public void should_return_products_list_when_get_all_products() {
        Product product = productRepository.create(TestHelper.productMap(3, "pen", "writing", 1299.99));
        final Response GET = get("products");
        assertThat(GET.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
        final List<Map<String, Object>> list = GET.readEntity(List.class);
        assertThat(list.size(), is(1));
    }

    @Test
    public void should_return_200_when_get_a_product() {
        Product product = productRepository.create(TestHelper.productMap(4, "banana", "delicious", 2.5));
        final Response GET = get("products/" + product.getId());
        assertThat(GET.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
        final Map<String, Object> productInfo = GET.readEntity(Map.class);
        assertThat(productInfo.get("uri"), is("/products/" + product.getId()));
    }
    
}
