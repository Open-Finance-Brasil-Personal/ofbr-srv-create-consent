package com.ammdev.ofbrsrvcreateconsent.adapters.persistence.repositories;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPermissionRepository extends JpaRepository<PermissionEntity, Long> {
}
