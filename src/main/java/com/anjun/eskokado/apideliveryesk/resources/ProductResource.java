package com.anjun.eskokado.apideliveryesk.resources;

import com.anjun.eskokado.apideliveryesk.domain.enums.StateDelivery;
import com.anjun.eskokado.apideliveryesk.domain.models.OrderItem;
import com.anjun.eskokado.apideliveryesk.domain.repositories.OrderItemRepository;
import com.anjun.eskokado.apideliveryesk.resources.dto.ResponseError;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    private final OrderItemRepository orderItemRepository;

    public ProductResource(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


    @GET
    @Path("{id}/orderItems")
    public Response findOrderByProductId(@PathParam("id") Long id) {
        List<OrderItem> orderItems = orderItemRepository.findByProductId(id);
        return Response.ok(orderItems).build();
    }
}
