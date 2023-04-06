package com.anjun.eskokado.apideliveryesk.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 150, nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "client")
    private List<Address> addresses;
}
