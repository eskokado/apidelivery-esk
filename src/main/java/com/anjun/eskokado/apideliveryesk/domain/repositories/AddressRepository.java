package com.anjun.eskokado.apideliveryesk.domain.repositories;

import com.anjun.eskokado.apideliveryesk.domain.models.Address;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {
    public Address findByClientId(Long clientId) {
        PanacheQuery<Address> query = find("client.id", clientId);
        return query.list().get(0);
    }
}
