package com.ammdev.ofbrsrvcreateconsent.application.port.incoming

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ClientEntity
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.LoggedUserDocumentDto
import com.ammdev.ofbrsrvcreateconsent.application.port.outgoing.GetClientPort
import com.ammdev.ofbrsrvcreateconsent.application.port.outgoing.SaveClientPort
import com.ammdev.ofbrsrvcreateconsent.application.services.ClientService
import spock.lang.Specification

class CreateClientUsecaseUnitTest extends Specification{

    ClientService clientService

    GetClientPort getClientPort = Mock(GetClientPort.class)
    SaveClientPort saveClientPort = Mock(SaveClientPort.class)

    def setup() {
        clientService = new ClientService(saveClientPort, getClientPort)
    }

    def "Deve criar um cliente com sucesso"() {
        given: "Informações do cliente"
        LoggedUserDocumentDto loggedUserDocumentDto = new LoggedUserDocumentDto(null, "12345678900", "CPF");

        and: "Ao buscar um cliente na base de dados, nada deve ser retornado"
        1 * getClientPort.findByIdentification(loggedUserDocumentDto.identification()) >> Optional.empty()

        and: "O id do cliente gerado deve ser"
        var uuid = UUID.randomUUID().toString()

        and: "A função de salvar deve ter sido chamada e retornado um cliente válido"
        1 * saveClientPort.save(loggedUserDocumentDto) >> new ClientEntity(uuid, loggedUserDocumentDto.identification(), loggedUserDocumentDto.rel())

        when: "O metodo de criar cliente for chamado"
        ClientEntity client = clientService.createClient(loggedUserDocumentDto)

        then: "O cliente deve ter sido criado"
        client

        and: "Seus valores devems ser preenchidos"
        client.id == uuid
        client.identification == "12345678900"
        client.rel == "CPF"
    }

    def "Não deve criar um cliente por já existir cliente na base de dados"() {
        given: "Informações do cliente"
        LoggedUserDocumentDto loggedUserDocumentDto = new LoggedUserDocumentDto(null, "12345678900", "CPF");

        and: "O id do cliente gerado deve ser"
        var uuid = UUID.randomUUID().toString()

        and: "O cliente na base de dados encontrado"
        var clientFounded = new ClientEntity(uuid, loggedUserDocumentDto.identification(), loggedUserDocumentDto.rel())

        and: "Ao buscar um cliente na base de dados, nada deve ser retornado"
        1 * getClientPort.findByIdentification(loggedUserDocumentDto.identification()) >> Optional.of(clientFounded)

        and: "A função de salvar deve ter sido chamada e retornado um cliente válido"
        0 * saveClientPort.save(loggedUserDocumentDto)

        when: "O metodo de criar cliente for chamado"
        ClientEntity client = clientService.createClient(loggedUserDocumentDto)

        then: "O cliente deve ter sido criado"
        client

        and: "Seus valores devems ser preenchidos"
        client.id == clientFounded.id
        client.identification == clientFounded.identification
        client.rel == clientFounded.rel
    }
}
