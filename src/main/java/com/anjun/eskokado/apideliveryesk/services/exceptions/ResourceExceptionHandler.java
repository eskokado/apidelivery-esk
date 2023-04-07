package com.anjun.eskokado.apideliveryesk.services.exceptions;

import com.anjun.eskokado.apideliveryesk.services.exceptions.ObjectNotFoundException;
import com.anjun.eskokado.apideliveryesk.services.exceptions.StandardError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceExceptionHandler implements ExceptionMapper<ObjectNotFoundException> {
    @Override
    public Response toResponse(ObjectNotFoundException e) {
        StandardError err = new StandardError(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage(), System.currentTimeMillis());
        return Response.status(Response.Status.NOT_FOUND).entity(err).build();
    }
}
