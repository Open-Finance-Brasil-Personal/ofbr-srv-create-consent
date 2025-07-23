package com.ammdev.ofbrsrvcreateconsent.application.services;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ClientEntity;
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.LoggedUserDocumentDto;
import com.ammdev.ofbrsrvcreateconsent.application.port.incoming.CreateClientUsecase;
import com.ammdev.ofbrsrvcreateconsent.application.port.outgoing.GetClientPort;
import com.ammdev.ofbrsrvcreateconsent.application.port.outgoing.SaveClientPort;

import java.util.Optional;

public class ClientService implements CreateClientUsecase {
    private final SaveClientPort saveClientPort;
    private final GetClientPort getClientPort;


    public ClientService(SaveClientPort saveClientPort, GetClientPort getClientPort) {
        this.saveClientPort = saveClientPort;
        this.getClientPort = getClientPort;
    }

    @Override
    public ClientEntity createClient(LoggedUserDocumentDto loggedUserDocumentDto) {
        String identification = loggedUserDocumentDto.identification();

        Optional<ClientEntity> clientEntity = getClientPort.findByIdentification(identification);

        return clientEntity.orElseGet(() -> saveClientPort.save(loggedUserDocumentDto));
    }
}
