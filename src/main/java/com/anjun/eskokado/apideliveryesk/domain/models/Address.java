package com.anjun.eskokado.apideliveryesk.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String number;

    @Column(length = 100)
    private String complement;

    @Column(name = "zip_code", length = 8, nullable = false)
    private String zipCode;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "addressOfDelivery")
    private List<Order> orders;
}
