package com.ammdev.ofbrsrvcreateconsent.application.port.incoming.fixtures

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.PermissionEntity
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.ResponseConsentDataDto
import com.ammdev.ofbrsrvcreateconsent.application.domain.enums.PermissionsEnum
import com.ammdev.ofbrsrvcreateconsent.application.domain.enums.StatusEnum

class ResponseConsentDataDtoFixture {

    static ResponseConsentDataDto getOne(String brand) {
        HashSet<PermissionsEnum> permissionsEnums = new HashSet<>()

        permissionsEnums.add(PermissionsEnum.ACCOUNTS_READ)
        permissionsEnums.add(PermissionsEnum.RESOURCES_READ)
        permissionsEnums.add(PermissionsEnum.LOANS_READ)

        String uuid = UUID.randomUUID().toString()

        String consentId = String.format("urn:%s:%s", brand, uuid)

        return ResponseConsentDataDto.builder()
                .consentId(consentId)
                .statusUpdateDateTime("2300-12-31T23:59:59")
                .expirationDateTime("2300-12-31T23:59:59")
                .creationDateTime("2025-12-31T23:59:59")
                .permissions(permissionsEnums)
                .status(StatusEnum.AWAITING_AUTHORISATION.getValue())
                .build()
    }
}
