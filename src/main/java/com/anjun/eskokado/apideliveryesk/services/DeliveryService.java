package com.anjun.eskokado.apideliveryesk.services;

import com.anjun.eskokado.apideliveryesk.domain.models.*;
import com.anjun.eskokado.apideliveryesk.domain.repositories.OrderItemRepository;
import com.anjun.eskokado.apideliveryesk.domain.repositories.OrderRepository;
import com.anjun.eskokado.apideliveryesk.resources.dto.CreateDeliveryRequest;
import com.anjun.eskokado.apideliveryesk.resources.dto.CreateDeliveryResponse;
import com.anjun.eskokado.apideliveryesk.resources.dto.ResponseError;
import com.anjun.eskokado.apideliveryesk.services.interfaces.IDeliveryService;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Set;

@ApplicationScoped
@Transactional
public class DeliveryService implements IDeliveryService {

    private final ClientService clientService;
    private final SupplierService supplierService;
    private final ProductService productService;
    private final AddressService addressService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final Validator validator;
    private final UserTransaction transaction;

    @Inject
    public DeliveryService(
            ClientService clientService,
            SupplierService supplierService,
            ProductService productService,
            AddressService addressService,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            Validator validator,
            UserTransaction transaction) {
        this.clientService = clientService;
        this.supplierService = supplierService;
        this.productService = productService;
        this.addressService = addressService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.validator = validator;
        this.transaction = transaction;
    }

    @Override
    public CreateDeliveryResponse createDelivery(CreateDeliveryRequest deliveryRequest) {
        CreateDeliveryResponse deliveryResponse = new CreateDeliveryResponse();
        Set<ConstraintViolation<CreateDeliveryRequest>> violations = validator.validate(deliveryRequest);
        if(!violations.isEmpty()){
            deliveryResponse.setErrors(Arrays.asList(ResponseError.createFromValidation(violations)));
            deliveryResponse.setStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);
            return deliveryResponse;
        }

        try {
            Client client = clientService.findById(deliveryRequest.getClientId());
            Supplier supplier = supplierService.findById(deliveryRequest.getSupplierId());
            Product product = productService.findById(deliveryRequest.getProductId());
            Address address = addressService.findByClientId(client.getId());
            Order order = new Order(address, supplier);
            OrderItem orderItem = new OrderItem(order, product, deliveryRequest.getDiscount(), deliveryRequest.getQuantity(), product.getPrice());
            orderRepository.persist(order);
            orderItemRepository.persist(orderItem);

            deliveryResponse.setOrder(order);
            deliveryResponse.setOrderItems(Arrays.asList(orderItem));
            deliveryResponse.setStatusCode(Response.Status.CREATED.getStatusCode());
        } catch (Exception e) {
            deliveryResponse.setErrors(Arrays.asList(new ResponseError(ResponseError.DEFAULT_ERROR_MESSAGE, null)));
            deliveryResponse.setStatusCode(Response.Status.BAD_REQUEST.getStatusCode());
        }

        return deliveryResponse;
    }
}
