package com.anjun.eskokado.apideliveryesk.services;

import com.anjun.eskokado.apideliveryesk.domain.models.Client;
import com.anjun.eskokado.apideliveryesk.domain.repositories.ClientRepository;
import com.anjun.eskokado.apideliveryesk.services.exceptions.ObjectNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ClientService {
    private ClientRepository clientRepository;

    @Inject
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findById(Long id) {
        Client obj = clientRepository.findById(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Object not found! Id: " + id
                    + ", Type: " + Client.class.getName());
        }
        return obj;
    }
}
