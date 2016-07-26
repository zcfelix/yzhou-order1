package com.thoughtworks.ketsu.web.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidParameterExceptionMapper implements ExceptionMapper<InvalidParameterException> {

    @Override
    public Response toResponse(InvalidParameterException exception) {
        exception.printStackTrace();
        return Response.status(400).type(MediaType.APPLICATION_JSON).entity(exception.getInvalidParameterInfos()).build();
    }
}
