package com.anjun.eskokado.apideliveryesk.resources;

import com.anjun.eskokado.apideliveryesk.domain.models.*;
import com.anjun.eskokado.apideliveryesk.domain.repositories.OrderItemRepository;
import com.anjun.eskokado.apideliveryesk.domain.repositories.OrderRepository;
import com.anjun.eskokado.apideliveryesk.resources.dto.CreateDeliveryRequest;
import com.anjun.eskokado.apideliveryesk.resources.dto.CreateDeliveryResponse;
import com.anjun.eskokado.apideliveryesk.resources.dto.ResponseError;
import com.anjun.eskokado.apideliveryesk.services.AddressService;
import com.anjun.eskokado.apideliveryesk.services.ClientService;
import com.anjun.eskokado.apideliveryesk.services.ProductService;
import com.anjun.eskokado.apideliveryesk.services.SupplierService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Set;

@Path("/deliveries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeliveryResource {

    private final ClientService clientService;
    private final SupplierService supplierService;
    private final ProductService productService;
    private final AddressService addressService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final Validator validator;

    @Inject
    public DeliveryResource(
            ClientService clientService,
            SupplierService supplierService,
            ProductService productService,
            AddressService addressService,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            Validator validator
    ) {
        this.clientService = clientService;
        this.supplierService = supplierService;
        this.productService = productService;
        this.addressService = addressService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.validator = validator;
    }

    @POST
    @Transactional
    public Response createDelivery(CreateDeliveryRequest deliveryRequest) {
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
            deliveryResponse.setErrors(Arrays.asList(new ResponseError(ResponseError.DEFAULT_ERROR_MESSAGE, null)));
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
        }
    }
}
