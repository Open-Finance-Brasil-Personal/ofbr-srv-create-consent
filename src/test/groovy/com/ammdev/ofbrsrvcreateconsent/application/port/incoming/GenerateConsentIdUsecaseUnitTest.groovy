package com.ammdev.ofbrsrvcreateconsent.application.port.incoming

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers.ConsentEntityMapper
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers.CreateConsentDataDtoMapper
import com.ammdev.ofbrsrvcreateconsent.application.port.outgoing.SaveConsentPort
import com.ammdev.ofbrsrvcreateconsent.application.services.ConsentService
import spock.lang.Specification

class GenerateConsentIdUsecaseUnitTest extends Specification {

    SaveConsentPort saveConsentPort = Mock(SaveConsentPort.class)

    CreateConsentDataDtoMapper createConsentDataDtoMapper = Mock(CreateConsentDataDtoMapper.class)
    ConsentEntityMapper consentEntityMapper = Mock(ConsentEntityMapper.class)

    GenerateConsentIdUsecase generateConsentIdUsecase =
            new ConsentService(saveConsentPort, createConsentDataDtoMapper, consentEntityMapper)

    def "deve validar a geração correta do identificador do consentimento"() {
        when: "O usecase for chamado"
        String consentId = generateConsentIdUsecase.generateConsentId(brand)

        then: "O identificador deve ter sido gerado"
        consentId != null
        consentId.length() > 0

        and: "Deve seguir as regras estipuladas pela febraban"
        var consentParts = consentId.split(":")
        consentParts.length == 3
        consentParts[0] == "urn"
        consentParts[1] == brand
        consentParts[2]
        consentParts[2].length() > 0

        where:
        brand << ["openfinancebrasil", "bancodobrasil", "nubank"]
    }
}