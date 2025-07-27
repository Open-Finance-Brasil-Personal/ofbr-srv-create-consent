package com.ammdev.ofbrsrvcreateconsent.application.services;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ClientEntity;
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.*;
import com.ammdev.ofbrsrvcreateconsent.application.port.incoming.CreateConsentResponseUsecase;

import java.time.LocalDateTime;

public class CreateConsentResponseService implements CreateConsentResponseUsecase {

    private final ClientService clientService;
    private final ConsentService consentService;

    public CreateConsentResponseService(ClientService clientService, ConsentService consentService) {
        this.clientService = clientService;
        this.consentService = consentService;
    }

    @Override
    public ResponseConsentDto create(CreateConsentDataDto createConsentDataDto, String brand, String link) {
        LoggedUserDocumentDto loggedUserDocumentDto = createConsentDataDto.loggedUser().document();

        ClientEntity clientEntity = this.clientService.createClient(loggedUserDocumentDto);

        LoggedUserDocumentDto clientDto = new LoggedUserDocumentDto(
                clientEntity.getId(),
                loggedUserDocumentDto.identification(),
                loggedUserDocumentDto.rel());

        ResponseConsentDataDto responseConsentDataDto =
                this.consentService.createConsent(createConsentDataDto, clientDto, brand);

        LinksDto links = new LinksDto(link);
        MetaDto meta = new MetaDto(LocalDateTime.now().toString());

        return new ResponseConsentDto(responseConsentDataDto, links, meta);
    }
}
