package com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ConsentEntity;
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.ResponseConsentDataDto;
import org.mapstruct.Mapper;

@Mapper(uses = PermissionMapper.class, componentModel = "spring")
public interface ConsentEntityMapper {
    ResponseConsentDataDto toResponseConsentDataDto(ConsentEntity consentEntity);
}
