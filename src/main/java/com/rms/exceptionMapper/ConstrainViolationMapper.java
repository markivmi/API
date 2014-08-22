package com.rms.exceptionMapper;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstrainViolationMapper implements ExceptionMapper<javax.validation.ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getMessage())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
