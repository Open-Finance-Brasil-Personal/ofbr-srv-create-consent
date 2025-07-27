package com.ammdev.ofbrsrvcreateconsent.adapters.persistence.repositories;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ClientEntity;
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers.ClientMapper;
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.LoggedUserDocumentDto;
import com.ammdev.ofbrsrvcreateconsent.application.port.outgoing.GetClientPort;
import com.ammdev.ofbrsrvcreateconsent.application.port.outgoing.SaveClientPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientRepository implements SaveClientPort, GetClientPort {

    private final SpringDataClientRepository repository;

    private final ClientMapper clientMapper;

    public ClientRepository(SpringDataClientRepository repository, ClientMapper clientMapper) {
        this.repository = repository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientEntity save(LoggedUserDocumentDto loggedUserDocumentDto) {
        return this.repository.save(clientMapper.loggedUserDocumentDtoToClientEntity(loggedUserDocumentDto));
    }

    @Override
    public Optional<ClientEntity> findByIdentification(String identification) {
        return this.repository.findByIdentification(identification);
    }
}
