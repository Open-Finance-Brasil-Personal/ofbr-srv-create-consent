package com.ammdev.ofbrsrvcreateconsent.application.port.outgoing;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ClientEntity;

import java.util.Optional;

public interface GetClientPort {
    Optional<ClientEntity> findByIdentification(String identification);
}
