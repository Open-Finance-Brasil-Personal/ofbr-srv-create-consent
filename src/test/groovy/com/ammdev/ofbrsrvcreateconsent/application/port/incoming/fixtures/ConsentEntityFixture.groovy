package com.ammdev.ofbrsrvcreateconsent.application.port.incoming.fixtures

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ConsentEntity
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.PermissionEntity
import com.ammdev.ofbrsrvcreateconsent.application.domain.enums.PermissionsEnum
import com.ammdev.ofbrsrvcreateconsent.application.domain.enums.StatusEnum

class ConsentEntityFixture {

    static ConsentEntity getOne(String brand) {
        HashSet<PermissionEntity> permissionEntities = new HashSet<>()

        permissionEntities.add(new PermissionEntity(PermissionsEnum.ACCOUNTS_READ.getId(), PermissionsEnum.ACCOUNTS_READ.getValue()))
        permissionEntities.add(new PermissionEntity(PermissionsEnum.RESOURCES_READ.getId(), PermissionsEnum.RESOURCES_READ.getValue()))
        permissionEntities.add(new PermissionEntity(PermissionsEnum.LOANS_READ.getId(), PermissionsEnum.LOANS_READ.getValue()))

        String uuid = UUID.randomUUID().toString()

        String consentId = String.format("urn:%s:%s", brand, uuid)

        return ConsentEntity.builder()
                .consentId(consentId)
                .client(ClientFixture.getOne())
                .statusUpdateDateTime("2300-12-31T23:59:59")
                .expirationDateTime("2300-12-31T23:59:59")
                .creationDateTime("2025-12-31T23:59:59")
                .permissions(permissionEntities)
                .status(StatusEnum.AWAITING_AUTHORISATION.getValue())
                .build()
    }
}
