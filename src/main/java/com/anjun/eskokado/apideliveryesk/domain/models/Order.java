package com.anjun.eskokado.apideliveryesk.domain.models;

import com.anjun.eskokado.apideliveryesk.domain.enums.StateDelivery;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "state_delivery")
    private Integer stateDelivery;

    @PrePersist
    public void prePersist(){
        setOrderDate(LocalDateTime.now());
        setStateDelivery(StateDelivery.PENDING);
    }

    public StateDelivery getStateDelivery() {
        return StateDelivery.toEnum(stateDelivery);
    }

    public void setStateDelivery(StateDelivery stateDelivery) {
        this.stateDelivery = stateDelivery.getCode();
    }
}
