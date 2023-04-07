package com.anjun.eskokado.apideliveryesk.services;

import com.anjun.eskokado.apideliveryesk.domain.models.Order;
import com.anjun.eskokado.apideliveryesk.domain.repositories.OrderRepository;
import com.anjun.eskokado.apideliveryesk.services.exceptions.ObjectNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class OrderService {
    private OrderRepository orderRepository;

    @Inject
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findById(Long id) {
        Order obj = orderRepository.findById(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Object not found! Id: " + id
                    + ", Type: " + Order.class.getName());
        }
        return obj;
    }
}
