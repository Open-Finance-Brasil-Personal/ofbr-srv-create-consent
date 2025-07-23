package com.ammdev.ofbrsrvcreateconsent.adapters.persistence.repositories;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataClientRepository extends JpaRepository<ClientEntity, String> {
    Optional<ClientEntity> findByIdentification(String identification);
}

