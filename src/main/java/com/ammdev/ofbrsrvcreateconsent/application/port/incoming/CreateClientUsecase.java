package com.ammdev.ofbrsrvcreateconsent.application.port.incoming;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ClientEntity;
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.LoggedUserDocumentDto;

public interface CreateClientUsecase {
    ClientEntity createClient(LoggedUserDocumentDto loggedUserDocumentDto);
}
