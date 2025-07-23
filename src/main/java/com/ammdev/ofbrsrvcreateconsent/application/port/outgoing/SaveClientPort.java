package com.ammdev.ofbrsrvcreateconsent.application.port.outgoing;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ClientEntity;
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.LoggedUserDocumentDto;

public interface SaveClientPort {
    ClientEntity save(LoggedUserDocumentDto loggedUserDocumentDto);
}
