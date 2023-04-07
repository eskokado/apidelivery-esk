package com.anjun.eskokado.apideliveryesk.resources.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateDeliveryRequest {
    @NotNull(message = "Client is Required")
    private Long clientId;
    @NotNull(message = "Supplier is Required")
    private Long supplierId;
    @NotNull(message = "Product is Required")
    private Long productId;
    private Double discount;
    @NotNull(message = "Quantity is Required")
    private Integer quantity;
}
