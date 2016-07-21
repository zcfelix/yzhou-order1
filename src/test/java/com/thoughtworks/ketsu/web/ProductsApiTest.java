package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class ProductsApiTest extends ApiSupport{
    private String productBaseUrl = "products";

    @Test
    public void should_return_201_when_create_valid_product() {
        Map<String, Object> productInfo = new HashMap() {{
            put("id", 1);
            put("name", "mac");
            put("desc", "computer");
            put("price", 13000);
        }};
        final Response POST = post("products", productInfo);
        assertThat(POST.getStatus(), is(201));
//        assertThat(POST.getLocation().toString(), containsString(productBaseUrl));
    }
}
