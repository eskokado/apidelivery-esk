package com.anjun.eskokado.apideliveryesk.domain.repositories;

import com.anjun.eskokado.apideliveryesk.domain.models.Supplier;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SupplierRepository implements PanacheRepository<Supplier> {
}
