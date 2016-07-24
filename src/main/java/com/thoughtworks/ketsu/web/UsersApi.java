package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.*;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Path("users")
public class UsersApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(HashMap<String, Object> userInfo,
                               @Context UserRepository userRepository,
                               @Context Routes routes) {

        return Response.created(routes.userUrl(userRepository.createUser(userInfo))).build();
    }
}
