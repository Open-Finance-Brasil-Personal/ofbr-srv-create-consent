package com.ammdev.ofbrsrvcreateconsent.adapters.persistence.repositories;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ClientEntity;
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers.LoggedUserDocumentDtoMapper;
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.LoggedUserDocumentDto;
import com.ammdev.ofbrsrvcreateconsent.application.port.outgoing.GetClientPort;
import com.ammdev.ofbrsrvcreateconsent.application.port.outgoing.SaveClientPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientRepository implements SaveClientPort, GetClientPort {

    private final SpringDataClientRepository repository;

    private final LoggedUserDocumentDtoMapper loggedUserDocumentDtoMapper;

    public ClientRepository(SpringDataClientRepository repository, LoggedUserDocumentDtoMapper loggedUserDocumentDtoMapper) {
        this.repository = repository;
        this.loggedUserDocumentDtoMapper = loggedUserDocumentDtoMapper;
    }

    @Override
    public ClientEntity save(LoggedUserDocumentDto loggedUserDocumentDto) {
        return this.repository.save(loggedUserDocumentDtoMapper.toClientEntity(loggedUserDocumentDto));
    }

    @Override
    public Optional<ClientEntity> findByIdentification(String identification) {
        return this.repository.findByIdentification(identification);
    }
}
