package com.anjun.eskokado.apideliveryesk.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
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

    public Address(String number, String complement, String zipCode, Client client) {
        this.number = number;
        this.complement = complement;
        this.zipCode = zipCode;
        this.client = client;
    }
}
