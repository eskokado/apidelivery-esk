package com.anjun.eskokado.apideliveryesk.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150, nullable = false, unique = true)
    private String name;
    @Column(precision = 10, scale = 2, columnDefinition = "NUMBER (10,2) DEFAULT 0.0")
    private Double price;

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();
}
