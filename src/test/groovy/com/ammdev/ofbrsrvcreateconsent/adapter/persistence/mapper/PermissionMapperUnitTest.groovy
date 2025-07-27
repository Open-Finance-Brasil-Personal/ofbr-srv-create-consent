package com.ammdev.ofbrsrvcreateconsent.adapter.persistence.mapper

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.PermissionEntity
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers.PermissionMapper
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.repositories.SpringDataPermissionRepository
import com.ammdev.ofbrsrvcreateconsent.application.domain.enums.PermissionsEnum
import spock.lang.Specification

class PermissionMapperUnitTest extends Specification {

    def permissionRepository = Mock(SpringDataPermissionRepository)
    def mapper = new PermissionMapper(permissionRepository)

    def "Deve mapear um conjunto de PermissionsEnum para PermissionEntity corretamente"() {
        given: "um conjunto de enums de permissões"

        def enums = new HashSet()
        enums.add(PermissionsEnum.ACCOUNTS_READ)
        enums.add(PermissionsEnum.LOANS_READ)

        and: "o repositório retorna entidades para cada ID"
        def account_read_permission = new PermissionEntity(PermissionsEnum.ACCOUNTS_READ.getId(),PermissionsEnum.ACCOUNTS_READ.getValue())
        def loans_read_permission = new PermissionEntity(PermissionsEnum.LOANS_READ.getId(),PermissionsEnum.LOANS_READ.getValue())

        permissionRepository.getReferenceById(1L) >> account_read_permission
        permissionRepository.getReferenceById(22L) >> loans_read_permission

        when: "o método toEntities é chamado"
        def result = mapper.toEntities(enums)

        then: "deve retornar um conjunto de PermissionEntity com os nomes corretos"
        result.size() == 2
        result.containsAll([account_read_permission, loans_read_permission])
    }

    def "Deve mapear um conjunto de PermissionEntity para PermissionsEnum corretamente"() {
        given: "um conjunto de PermissionEntity"
        def entities = [
                new PermissionEntity(id: 1L, name: "ACCOUNTS_READ"),
                new PermissionEntity(id: 22L, name: "LOANS_READ")
        ] as Set

        when: "o método toEnum é chamado"
        def result = mapper.toEnum(entities)

        then: "deve retornar os enums correspondentes"
        result.containsAll([PermissionsEnum.ACCOUNTS_READ, PermissionsEnum.LOANS_READ])
    }
}