package com.ammdev.ofbrsrvcreateconsent.application.port.incoming


import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.*
import com.ammdev.ofbrsrvcreateconsent.application.port.incoming.fixtures.ClientFixture
import com.ammdev.ofbrsrvcreateconsent.application.port.incoming.fixtures.CreateConsentDataDtoFixture
import com.ammdev.ofbrsrvcreateconsent.application.port.incoming.fixtures.ResponseConsentDataDtoFixture
import com.ammdev.ofbrsrvcreateconsent.application.services.ClientService
import com.ammdev.ofbrsrvcreateconsent.application.services.ConsentService
import com.ammdev.ofbrsrvcreateconsent.application.services.CreateConsentResponseService
import spock.lang.Specification

class CreateConsentResponseServiceUnitTest extends Specification {

    def clientService = Mock(ClientService)
    def consentService = Mock(ConsentService)

    def createConsentResponseService = new CreateConsentResponseService(clientService, consentService)

    def "Deve criar uma resposta de consentimento corretamente com todos os dados"() {
        given: "um DTO de entrada com dados do usuário logado"
        var createConsentDto = CreateConsentDataDtoFixture.getOne()
        var loggedUserDocumentDto = createConsentDto.loggedUser().document()

        and: "um clientEntity simulado retornado pelo clientService"
        def clientEntity = ClientFixture.getOne()

        and: "Para a marca openfinancebrasil"
        var brand = "openfinancebrasil"

        and: "Um cliente deve ser criado"
        1 * clientService.createClient(loggedUserDocumentDto) >> clientEntity

        and: "Um clientDto deve ser montado"
        var clientDto = LoggedUserDocumentDto.builder()
                                                .id(clientEntity.id)
                                                .identification(clientEntity.identification)
                                                .rel(clientEntity.rel)
                                                .build()

        and: "um DTO de resposta de consentimento retornado pelo consentService"
        def responseConsentDataDto = ResponseConsentDataDtoFixture.getOne(brand)
        1 * consentService.createConsent(createConsentDto, clientDto, brand) >> responseConsentDataDto

        and: "um link de consentimento e marca"
        def link = String.format("https://consentimentos.%s.com", brand)

        when: "a resposta de consentimento é criada"
        def result = createConsentResponseService.create(createConsentDto, brand, link)

        then: "o DTO de resposta deve conter os dados esperados"
        result != null
        result.data() == responseConsentDataDto
        result.links().self() == link
        result.meta().requestDateTime() != null
    }
}