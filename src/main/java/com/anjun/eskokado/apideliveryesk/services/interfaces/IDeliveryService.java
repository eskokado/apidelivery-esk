package com.anjun.eskokado.apideliveryesk.services.interfaces;

import com.anjun.eskokado.apideliveryesk.resources.dto.CreateDeliveryRequest;
import com.anjun.eskokado.apideliveryesk.resources.dto.CreateDeliveryResponse;

public interface IDeliveryService {
    CreateDeliveryResponse createDelivery(CreateDeliveryRequest deliveryRequest);
}
