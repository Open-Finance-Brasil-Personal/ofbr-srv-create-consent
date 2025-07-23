package com.ammdev.ofbrsrvcreateconsent.adapters.persistence.repositories;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ConsentEntity;
import com.ammdev.ofbrsrvcreateconsent.application.port.outgoing.SaveConsentPort;
import org.springframework.stereotype.Repository;

@Repository
public class ConsentRepository implements SaveConsentPort {

    private final SpringDataConsentRepository repository;

    public ConsentRepository(SpringDataConsentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(ConsentEntity consentEntity) {
        this.repository.save(consentEntity);
    }
}
