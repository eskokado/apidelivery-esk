package com.anjun.eskokado.apideliveryesk.domain.repositories;

import com.anjun.eskokado.apideliveryesk.domain.models.Address;
import com.anjun.eskokado.apideliveryesk.domain.models.Order;
import com.anjun.eskokado.apideliveryesk.domain.models.OrderItem;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class OrderItemRepository implements PanacheRepository<OrderItem> {
    public List<OrderItem> findByProductId(Long productId) {
        PanacheQuery<OrderItem> query = find("id.product.id", productId);
        return query.list();
    }
}
