package com.anjun.eskokado.apideliveryesk.resources.interfaces;

import com.anjun.eskokado.apideliveryesk.domain.models.ZipCodeAddress;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient
@Path("/ws")
public interface ZipCodeClient {
    @GET
    @Path("/{zipCode}/json")
    @Produces(MediaType.APPLICATION_JSON)
    public ZipCodeAddress getZipCodeAddress(@PathParam(value = "zipCode") String zipCode);
}
