package com.anjun.eskokado.apideliveryesk.resources.exceptions;

import com.anjun.eskokado.apideliveryesk.resources.dto.ResponseError;
import com.anjun.eskokado.apideliveryesk.services.exceptions.ObjectNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceExceptionHandler implements ExceptionMapper<ObjectNotFoundException> {
    @Override
    public Response toResponse(ObjectNotFoundException e) {
        ResponseError responseError = new ResponseError(e.getMessage(), null);
        return new ResponseError(e.getMessage(), null)
                .withStatusCode(Response.Status.NOT_FOUND.getStatusCode());
    }
}
