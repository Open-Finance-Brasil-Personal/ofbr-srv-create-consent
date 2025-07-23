package com.ammdev.ofbrsrvcreateconsent.application.port.incoming;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ClientEntity;
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.CreateConsentDataDto;
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.ResponseConsentDataDto;

public interface CreateConsentUsecase {
   ResponseConsentDataDto createConsent(CreateConsentDataDto createConsentDataDto, ClientEntity clientEntity, String brand);
}
