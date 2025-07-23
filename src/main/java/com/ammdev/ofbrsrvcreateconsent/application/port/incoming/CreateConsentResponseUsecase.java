package com.ammdev.ofbrsrvcreateconsent.application.port.incoming;

import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.CreateConsentDataDto;
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.ResponseConsentDto;

public interface CreateConsentResponseUsecase {
    ResponseConsentDto create(CreateConsentDataDto createConsentDataDto, String brand, String link);
}
