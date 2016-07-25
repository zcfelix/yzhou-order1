package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.User;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response createOrderForUser(Map<String, Object> info) {
        return Response.status(201).build();
    }
}
