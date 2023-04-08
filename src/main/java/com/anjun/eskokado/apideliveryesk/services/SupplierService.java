package com.anjun.eskokado.apideliveryesk.services;

import com.anjun.eskokado.apideliveryesk.domain.models.Supplier;
import com.anjun.eskokado.apideliveryesk.domain.repositories.SupplierRepository;
import com.anjun.eskokado.apideliveryesk.services.exceptions.ObjectNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SupplierService {
    private SupplierRepository supplierRepository;

    @Inject
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier findById(Long id) {
        Supplier obj = supplierRepository.findById(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Object not found! Id: " + id
                    + ", Type: " + Supplier.class.getName());
        }
        return obj;
    }
}
