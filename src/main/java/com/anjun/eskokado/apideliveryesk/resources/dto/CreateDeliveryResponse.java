package com.anjun.eskokado.apideliveryesk.resources.dto;

import com.anjun.eskokado.apideliveryesk.domain.models.*;
import lombok.Data;

import java.util.List;

@Data
public class CreateDeliveryResponse {
    private Order order;
    private List<OrderItem> orderItems;
    private List<ResponseError> errors;
    private ZipCodeAddress zipCodeAddress;
    private int statusCode;
}
