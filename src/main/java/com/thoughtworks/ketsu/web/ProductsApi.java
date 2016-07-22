package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;
import com.thoughtworks.ketsu.web.validator.ProductValidator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.HashMap;

@Path("products")
public class ProductsApi {
    @Context
    ProductRepository productRepository;

    @Context
    Routes routes;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(HashMap<String, Object> info) {
        productRepository.create(info);

        Product product = productRepository.findById((int)info.get("id"));
        ProductValidator productValidator = new ProductValidator();

        if (productValidator.isValidate(info)) {
            return Response.created(routes.productUrl(product)).build();
            //return Response.status(201).build();
        } else {
            //throw new WebApplicationException(Response.Status.BAD_REQUEST);
            return Response.status(400).build();
        }
    }

}
