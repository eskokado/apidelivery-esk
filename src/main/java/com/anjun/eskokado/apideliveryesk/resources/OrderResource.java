package com.anjun.eskokado.apideliveryesk.resources;

import com.anjun.eskokado.apideliveryesk.domain.enums.StateDelivery;
import com.anjun.eskokado.apideliveryesk.domain.models.*;
import com.anjun.eskokado.apideliveryesk.domain.repositories.OrderItemRepository;
import com.anjun.eskokado.apideliveryesk.domain.repositories.OrderRepository;
import com.anjun.eskokado.apideliveryesk.resources.dto.CreateDeliveryRequest;
import com.anjun.eskokado.apideliveryesk.resources.dto.CreateDeliveryResponse;
import com.anjun.eskokado.apideliveryesk.resources.dto.ResponseError;
import com.anjun.eskokado.apideliveryesk.services.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    private final ClientService clientService;
    private final SupplierService supplierService;
    private final ProductService productService;
    private final AddressService addressService;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final Validator validator;

    @Inject
    public OrderResource(
            ClientService clientService,
            SupplierService supplierService,
            ProductService productService,
            AddressService addressService,
            OrderService orderService,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            Validator validator
    ) {
        this.clientService = clientService;
        this.supplierService = supplierService;
        this.productService = productService;
        this.addressService = addressService;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.validator = validator;
    }

    @POST
    @Transactional
    public Response createOrder(CreateDeliveryRequest deliveryRequest) {
        Set<ConstraintViolation<CreateDeliveryRequest>> violations = validator.validate(deliveryRequest);
        if(!violations.isEmpty()){
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);
        }
        CreateDeliveryResponse deliveryResponse = new CreateDeliveryResponse();
        Client client = clientService.findById(deliveryRequest.getClientId());
        Supplier supplier = supplierService.findById(deliveryRequest.getSupplierId());
        Product product = productService.findById(deliveryRequest.getProductId());
        Address address = addressService.findByClientId(client.getId());
        try {
            Order order = new Order(address, supplier);
            OrderItem orderItem = new OrderItem(order, product, deliveryRequest.getDiscount(), deliveryRequest.getQuantity(), product.getPrice());

            orderRepository.persist(order);
            orderItemRepository.persist(orderItem);

            deliveryResponse.setOrder(order);
            deliveryResponse.setOrderItems(Arrays.asList(orderItem));
            return Response.ok(deliveryResponse).status(Response.Status.CREATED.getStatusCode()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), ResponseError.DEFAULT_ERROR_MESSAGE).build();
        }
    }

    @POST
    @Path("{orderId}/delivered")
    @Transactional
    public Response orderDelivered(@PathParam("orderId") Long orderId) {
        Order order = orderService.findById(orderId);
        order.setStateDelivery(StateDelivery.DELIVERED);
        try {
            orderRepository.persist(order);
            return Response.ok(order).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), ResponseError.DEFAULT_ERROR_MESSAGE).build();
        }
    }
    @POST
    @Path("{orderId}/cancel")
    @Transactional
    public Response orderToCancel(@PathParam("orderId") Long orderId) {
        Order order = orderService.findById(orderId);
        order.setStateDelivery(StateDelivery.CANCELED);
        try {
            orderRepository.persist(order);
            return Response.ok(order).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), ResponseError.DEFAULT_ERROR_MESSAGE).build();
        }
    }
}
