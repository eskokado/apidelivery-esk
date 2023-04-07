package com.anjun.eskokado.apideliveryesk.services;

import com.anjun.eskokado.apideliveryesk.domain.models.Address;
import com.anjun.eskokado.apideliveryesk.domain.models.Product;
import com.anjun.eskokado.apideliveryesk.domain.repositories.AddressRepository;
import com.anjun.eskokado.apideliveryesk.domain.repositories.ProductRepository;
import com.anjun.eskokado.apideliveryesk.services.exceptions.ObjectNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AddressService {
    private AddressRepository addressRepository;

    @Inject
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address findByClientId(Long clientId) {
        Address obj = addressRepository.findByClientId(clientId);
        if (obj == null) {
            throw new ObjectNotFoundException("Object not found! Id: " + clientId
                    + ", Type: " + Address.class.getName());
        }
        return obj;
    }
}
