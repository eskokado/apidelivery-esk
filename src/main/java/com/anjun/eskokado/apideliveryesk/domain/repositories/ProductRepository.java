package com.anjun.eskokado.apideliveryesk.domain.repositories;

import com.anjun.eskokado.apideliveryesk.domain.models.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {
}
