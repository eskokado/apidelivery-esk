package com.anjun.eskokado.apideliveryesk.domain.models;

import com.anjun.eskokado.apideliveryesk.domain.enums.StateDelivery;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "state_delivery")
    private Integer stateDelivery;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "address_of_delivery_id")
    private Address addressOfDelivery;

    @ManyToOne
    private Supplier supplier;

    public Order(Address addressOfDelivery, Supplier supplier) {
        this.addressOfDelivery = addressOfDelivery;
        this.supplier = supplier;
    }

    @PrePersist
    public void prePersist(){
        setOrderDate(LocalDateTime.now());
        setStateDelivery(StateDelivery.PENDING);
    }

    public List<Product> getProducts() {
        List<Product> list = new ArrayList<>();
        items.stream().map(x -> list.add(x.getProduct()));
        return list;
    }

    public StateDelivery getStateDelivery() {
        return StateDelivery.toEnum(stateDelivery);
    }

    public void setStateDelivery(StateDelivery stateDelivery) {
        this.stateDelivery = stateDelivery.getCode();
    }
}
