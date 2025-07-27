package com.ammdev.ofbrsrvcreateconsent.application.port.incoming.fixtures

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ClientEntity

class ClientFixture {

    static ClientEntity getOne() {
        return ClientEntity.builder()
                .id(UUID.randomUUID().toString())
                .identification("12345678903")
                .rel("CPF")
                .build()
    }
}
