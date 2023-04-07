package com.anjun.eskokado.apideliveryesk.resources;

import com.anjun.eskokado.apideliveryesk.resources.dto.CreateDeliveryRequest;
import com.anjun.eskokado.apideliveryesk.resources.dto.CreateDeliveryResponse;
import com.anjun.eskokado.apideliveryesk.services.interfaces.IDeliveryService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/deliveries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeliveryResource {

    private final IDeliveryService deliveryService;
    @Inject
    public DeliveryResource(IDeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @POST
    public Response createDelivery(CreateDeliveryRequest deliveryRequest) {
        CreateDeliveryResponse response = deliveryService.createDelivery(deliveryRequest);
        return Response.ok(response).status(Response.Status.CREATED).build();
    }
}
