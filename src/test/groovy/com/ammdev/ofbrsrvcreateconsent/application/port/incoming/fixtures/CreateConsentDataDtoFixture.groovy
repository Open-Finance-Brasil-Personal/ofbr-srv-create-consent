package com.ammdev.ofbrsrvcreateconsent.application.port.incoming.fixtures

import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.BusinessEntityDocumentDto
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.BusinessEntityDto
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.CreateConsentDataDto
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.LoggedUserDocumentDto
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.LoggedUserDto
import com.ammdev.ofbrsrvcreateconsent.application.domain.enums.PermissionsEnum

class CreateConsentDataDtoFixture {
    static CreateConsentDataDto getOne() {
        HashSet<PermissionsEnum> permissionsEnums = new HashSet<>()

        permissionsEnums.add(PermissionsEnum.ACCOUNTS_READ)
        permissionsEnums.add(PermissionsEnum.RESOURCES_READ)
        permissionsEnums.add(PermissionsEnum.LOANS_READ)

        return CreateConsentDataDto.builder()
                .loggedUser(
                        LoggedUserDto.builder()
                                .document(
                                        LoggedUserDocumentDto.builder()
                                                .identification("12345678903")
                                                .rel("CPF")
                                                .build()
                                )
                                .build()
                )
                .businessEntity(
                        BusinessEntityDto.builder()
                                .document(
                                        BusinessEntityDocumentDto.builder()
                                                .identification("18765432100")
                                                .rel("CNPJ")
                                                .build()
                                )
                                .build()
                )
                .permissions(permissionsEnums)
                .expirationDateTime("2300-12-31T23:59:59")
                .build()
    }
}
