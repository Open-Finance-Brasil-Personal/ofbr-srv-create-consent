package com.ammdev.ofbrsrvcreateconsent.application.port.outgoing;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ConsentEntity;

public interface SaveConsentPort {
    void save(ConsentEntity consentEntity);
}
