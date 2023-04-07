package com.anjun.eskokado.apideliveryesk.services;

import com.anjun.eskokado.apideliveryesk.domain.models.Product;
import com.anjun.eskokado.apideliveryesk.domain.models.Supplier;
import com.anjun.eskokado.apideliveryesk.domain.repositories.ProductRepository;
import com.anjun.eskokado.apideliveryesk.domain.repositories.SupplierRepository;
import com.anjun.eskokado.apideliveryesk.services.exceptions.ObjectNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ProductService {
    private ProductRepository productRepository;

    @Inject
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id) {
        Product obj = productRepository.findById(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Object not found! Id: " + id
                    + ", Type: " + Product.class.getName());
        }
        return obj;
    }
}
