package com.ammdev.ofbrsrvcreateconsent.application.services;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ClientEntity;
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ConsentEntity;
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers.ConsentEntityMapper;
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers.CreateConsentDataDtoMapper;
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers.ClientMapper;
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.*;
import com.ammdev.ofbrsrvcreateconsent.application.port.incoming.CreateConsentUsecase;
import com.ammdev.ofbrsrvcreateconsent.application.port.incoming.GenerateConsentIdUsecase;
import com.ammdev.ofbrsrvcreateconsent.application.port.outgoing.SaveConsentPort;

public class ConsentService implements CreateConsentUsecase, GenerateConsentIdUsecase {

    private final SaveConsentPort saveConsentPort;

    private final CreateConsentDataDtoMapper consentDataDtoMapper;
    private final ConsentEntityMapper consentEntityMapper;
    private final ClientMapper clientMapper;

    public ConsentService(SaveConsentPort saveConsentPort,
                          CreateConsentDataDtoMapper consentDataDtoMapper,
                          ConsentEntityMapper consentEntityMapper,
                          ClientMapper clientMapper) {
        this.saveConsentPort = saveConsentPort;
        this.consentDataDtoMapper = consentDataDtoMapper;
        this.consentEntityMapper = consentEntityMapper;
        this.clientMapper = clientMapper;
    }

    @Override
    public ResponseConsentDataDto createConsent(CreateConsentDataDto createConsentDataDto, LoggedUserDocumentDto clientDto, String brand) {
        ConsentEntity consentEntity = buildConsentEntityToSave(createConsentDataDto, clientDto, brand);
        saveConsentPort.save(consentEntity);

        return consentEntityMapper.toResponseConsentDataDto(consentEntity);
    }

    @Override
    public String generateConsentId(String brand) {
        String urn = "urn";
        String uuid = java.util.UUID.randomUUID().toString();

        return String.format("%s:%s:%s", urn, brand, uuid);
    }

    private ConsentEntity buildConsentEntityToSave(CreateConsentDataDto createConsentDataDto, LoggedUserDocumentDto clientDto, String brand) {
        ConsentEntity consentEntity = consentDataDtoMapper.toConsentEntity(createConsentDataDto);
        ClientEntity clientEntity = clientMapper.loggedUserDocumentDtoToClientEntity(clientDto);

        consentEntity.setConsentId(this.generateConsentId(brand));
        consentEntity.setClient(clientEntity);

        return consentEntity;
    }
}
