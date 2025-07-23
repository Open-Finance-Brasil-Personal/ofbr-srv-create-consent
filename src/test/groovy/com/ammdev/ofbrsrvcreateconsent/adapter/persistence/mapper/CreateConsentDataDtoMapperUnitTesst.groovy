package com.ammdev.ofbrsrvcreateconsent.adapter.persistence.mapper

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ConsentEntity
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.BusinessEntityDocumentDto
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.BusinessEntityDto
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.CreateConsentDataDto
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers.CreateConsentDataDtoMapper
import com.ammdev.ofbrsrvcreateconsent.application.domain.enums.PermissionsEnum
import spock.lang.Specification

class CreateConsentDataDtoMapperUnitTesst extends Specification {

    def "Deve transformar CreateConsentDataDto em ConsentEntity com todos os campos"() {
        given: "Um documento com CNPJ"
        def businessDocument = new BusinessEntityDocumentDto("72556966000110", "CNPJ")
        def businessEntity = new BusinessEntityDto(businessDocument)

        and: "Uma lista de permissões"
        def permissionsList = Set.of(PermissionsEnum.LOANS_READ, PermissionsEnum.FUNDS_READ)

        and: "Gera o objeto que deverá ser transformado em entidade"
        def dto = new CreateConsentDataDto(
            null,
            businessEntity,
            permissionsList,
            "2025-12-31T23:59:59"
        )

        when: "O mapper é invocado"
        ConsentEntity entity = CreateConsentDataDtoMapper.INSTANCE.toConsentEntity(dto)

        then: "Valida as informações transformadas"
        entity.businessIdentification == "72556966000110"
        entity.businessRel == "CNPJ"
        entity.expirationDateTime == "2025-12-31T23:59:59"

        and: "Valida as permissões do cliente"
        entity.permissions != null
        entity.permissions.size() == 2
        entity.permissions.getAt(0).getName() == PermissionsEnum.LOANS_READ.getValue()
        entity.permissions.getAt(1).getName() == PermissionsEnum.FUNDS_READ.getValue()

        and: "Valida as datas geradas"
        entity.creationDateTime != null
        entity.statusUpdateDateTime != null
    }
}
