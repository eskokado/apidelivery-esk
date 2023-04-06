package com.anjun.eskokado.apideliveryesk.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
@Data
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 150, nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "supplier")
    private List<Order> orders;

    public Supplier(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
