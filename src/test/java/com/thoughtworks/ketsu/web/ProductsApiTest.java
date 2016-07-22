package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.util.Json;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class ProductsApiTest extends ApiSupport{

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void should_return_201_and_location_when_create_valid_product() {
        Map<String, Object> productInfo = new HashMap() {{
            put("id", 1);
            put("name", "mac");
            put("desc", "computer");
            put("price", 13000);
        }};
        final Response POST = post("products", productInfo);
        assertThat(POST.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
        assertThat(Pattern.matches(".*?/products/[0-9-]*", POST.getLocation().toASCIIString()), is(true));
    }

    @Test
    public void should_return_400_when_create_invalid_product() {
        Map<String, Object> productInfo = new HashMap() {{
            put("id", 2);
            put("name", "");
            put("desc", "delicious");
            put("price", 3);
        }};
        final Response POST = post("products", productInfo);
        assertThat(POST.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }
}
