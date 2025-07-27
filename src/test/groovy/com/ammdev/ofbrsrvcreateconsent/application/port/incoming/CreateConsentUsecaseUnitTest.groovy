package com.ammdev.ofbrsrvcreateconsent.application.port.incoming

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers.ClientMapper
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers.ConsentEntityMapper
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers.CreateConsentDataDtoMapper
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.ResponseConsentDataDto
import com.ammdev.ofbrsrvcreateconsent.application.domain.enums.PermissionsEnum
import com.ammdev.ofbrsrvcreateconsent.application.domain.enums.StatusEnum
import com.ammdev.ofbrsrvcreateconsent.application.port.incoming.fixtures.ConsentEntityFixture
import com.ammdev.ofbrsrvcreateconsent.application.port.incoming.fixtures.CreateConsentDataDtoFixture
import com.ammdev.ofbrsrvcreateconsent.application.port.incoming.fixtures.ResponseConsentDataDtoFixture
import com.ammdev.ofbrsrvcreateconsent.application.port.outgoing.SaveConsentPort
import com.ammdev.ofbrsrvcreateconsent.application.services.ConsentService
import spock.lang.Specification

class CreateConsentUsecaseUnitTest extends Specification {

    def saveConsentPort = Mock(SaveConsentPort)
    def consentDataDtoMapper = Mock(CreateConsentDataDtoMapper)
    def consentEntityMapper = Mock(ConsentEntityMapper)
    def clientMapper = Mock(ClientMapper)

    def consentService = new ConsentService(saveConsentPort, consentDataDtoMapper, consentEntityMapper, clientMapper)

    def "Deve criar um consentimento corretamente a partir dos dados informados"() {
        given: "um DTO de criação de consentimento, um cliente e uma marca"
        def createConsentDataDto = CreateConsentDataDtoFixture.getOne()
        def brand = "banco-exemplo"

        and: "uma entidade de consentimento construída a partir do DTO"
        def consentEntity = ConsentEntityFixture.getOne(brand)
        1 * consentDataDtoMapper.toConsentEntity(createConsentDataDto) >> consentEntity

        and: "um DTO de resposta que será retornado"
        def responseDto = ResponseConsentDataDtoFixture.getOne(brand)
        1 * consentEntityMapper.toResponseConsentDataDto(consentEntity) >> responseDto

        when: "o consentimento é criado"
        def result = consentService.createConsent(
                createConsentDataDto,
                createConsentDataDto.loggedUser().document(),
                brand
        )

        then: "a entidade deve ter o consentId gerado corretamente"
        result.consentId()
        result.consentId().startsWith("urn:$brand:")

        and: "As datas devem vir preenchidas"
        result.creationDateTime()
        result.expirationDateTime()
        result.statusUpdateDateTime()

        and: "Com o status AWAITING_AUTHORIZATION"
        result.status() == StatusEnum.AWAITING_AUTHORISATION.getValue()

        and: "Contendo as permissões: ACCOUNTS_READ, RESOURCES_READ e LOANS_READ"
        result.permissions()
        result.permissions().size() == 3

        result.permissions().contains(PermissionsEnum.ACCOUNTS_READ)
        result.permissions().contains(PermissionsEnum.RESOURCES_READ)
        result.permissions().contains(PermissionsEnum.LOANS_READ)

        and: "o consentimento deve ser salvo"
        1 * saveConsentPort.save(_)
    }
}
