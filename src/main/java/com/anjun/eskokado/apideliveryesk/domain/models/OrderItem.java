package com.anjun.eskokado.apideliveryesk.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
public class OrderItem {

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

   @Column(precision = 10, scale = 2, columnDefinition = "DECIMAL (10,2) DEFAULT 0.0")
    private Double discount;
    private Integer quantity;
    @Column(precision = 10, scale = 2, columnDefinition = "DECIMAL (10,2) DEFAULT 0.0")
    private Double price;

    public OrderItem(Order order, Product product, Double discount, Integer quantity, Double price) {
        super();
        id.setOrder(order);
        id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public Order getOrder() {
        return id.getOrder();
    }

    public Product getProduct() {
        return id.getProduct();
    }
}
