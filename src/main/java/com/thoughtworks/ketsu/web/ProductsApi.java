package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.web.exception.InvalidParameterException;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("products")
public class ProductsApi {
    @Context
    ProductRepository productRepository;

    @Context
    Routes routes;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(HashMap<String, Object> info) {
        List<String> fields = new ArrayList<>();

        if(info.getOrDefault("name", "").toString().trim().isEmpty())
            fields.add("name");
        if(info.getOrDefault("desc", "").toString().trim().isEmpty())
            fields.add("desc");
        if(info.getOrDefault("price", "").toString().trim().isEmpty())
            fields.add("price");
        if(fields.size() > 0)
            throw new InvalidParameterException(fields);
        return Response.created(routes.productUrl(productRepository.create(info))).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product findProduct(@PathParam("id") int id) {
        return productRepository.findById(id).orElseThrow( () -> new NotFoundException("product note found"));
    }

}
