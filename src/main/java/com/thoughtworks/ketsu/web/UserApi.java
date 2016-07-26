package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.web.exception.InvalidParameterException;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserApi {
    private User user;

    public UserApi(User user) { this.user = user; }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() { return user; }

    @POST
    @Path("orders")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrderForUser(Map<String, Object> info,
                                       @Context Routes routes) {
        List<String> fields = new ArrayList<>();
        if (info.getOrDefault("name", "").toString().trim().isEmpty())
            fields.add("name");
        if (info.getOrDefault("address", "").toString().trim().isEmpty())
            fields.add("address");
        if (info.getOrDefault("phone", "").toString().trim().isEmpty())
            fields.add("phone");
        if (fields.size() > 0)
            throw new InvalidParameterException(fields);
        Order order = user.createOrder(info);
        return Response.created(routes.orderUrl(order)).build();
        //return Response.status(201).build();
    }

    @GET
    @Path("orders/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order findOderById(@PathParam("orderId") int orderId) {
        return user.findById(orderId).orElseThrow(() -> new NotFoundException("order not found"));
        // return Response.status(200).build();
    }
}
