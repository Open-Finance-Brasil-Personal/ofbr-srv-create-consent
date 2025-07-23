package com.ammdev.ofbrsrvcreateconsent.adapters.persistence.repositories;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ConsentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataConsentRepository extends JpaRepository<ConsentEntity, String> {
}
