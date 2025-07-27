package com.ammdev.ofbrsrvcreateconsent.adapters.configuration;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers.ConsentEntityMapper;
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers.CreateConsentDataDtoMapper;
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers.ClientMapper;
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.repositories.ClientRepository;
import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.repositories.ConsentRepository;
import com.ammdev.ofbrsrvcreateconsent.application.services.ClientService;
import com.ammdev.ofbrsrvcreateconsent.application.services.ConsentService;
import com.ammdev.ofbrsrvcreateconsent.application.services.CreateConsentResponseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ConsentService getConsentService(ConsentRepository consentRepository,
                                     CreateConsentDataDtoMapper createConsentDataDtoMapper,
                                     ConsentEntityMapper consentEntityMapper,
                                     ClientMapper clientMapper) {
        return new ConsentService(
                consentRepository,
                createConsentDataDtoMapper,
                consentEntityMapper,
                clientMapper
        );
    }

    @Bean
    ClientService getClientService(ClientRepository clientRepository) {
        return new ClientService(clientRepository, clientRepository);
    }

    @Bean
    CreateConsentResponseService getCreateConsentResponseService(ClientService clientService, ConsentService consentService) {
        return new CreateConsentResponseService(clientService, consentService);
    }
}
