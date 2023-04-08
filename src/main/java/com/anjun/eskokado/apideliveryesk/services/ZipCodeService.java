package com.anjun.eskokado.apideliveryesk.services;

import com.anjun.eskokado.apideliveryesk.domain.models.ZipCodeAddress;
import com.anjun.eskokado.apideliveryesk.resources.interfaces.ZipCodeClient;
import io.quarkus.cache.CacheResult;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ZipCodeService {
    final Logger log = LoggerFactory.getLogger(ZipCodeService.class);

    @RestClient
    @Inject
    ZipCodeClient zipCodeClient;

    @CacheResult(cacheName = "zip-code-cache")
    public ZipCodeAddress getZipCodeAddress(String zipCode) {
        ZipCodeAddress zipCodeAddress = zipCodeClient.getZipCodeAddress(zipCode);
        log.info("Fez a chamada na API de zipCodeAddress: " + zipCode);
        return zipCodeAddress;
    }
}
