package com.anjun.eskokado.apideliveryesk.domain.models;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
public class OrderItemPK implements Serializable {
    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;
}
