package com.anjun.eskokado.apideliveryesk.domain.repositories;

import com.anjun.eskokado.apideliveryesk.domain.models.OrderItem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderItemRepository implements PanacheRepository<OrderItem> {
}
