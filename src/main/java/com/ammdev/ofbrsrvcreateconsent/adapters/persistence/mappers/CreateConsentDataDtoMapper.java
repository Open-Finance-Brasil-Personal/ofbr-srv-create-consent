package com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ConsentEntity;
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.CreateConsentDataDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = PermissionMapper.class, componentModel = "spring")
public interface CreateConsentDataDtoMapper {
    @Mapping(source = "businessEntity.document.identification", target = "businessIdentification")
    @Mapping(source = "businessEntity.document.rel", target = "businessRel")
    @Mapping(target = "creationDateTime", expression = "java(java.time.LocalDateTime.now().toString())")
    @Mapping(target = "statusUpdateDateTime", expression = "java(java.time.LocalDateTime.now().toString())")
    @Mapping(target = "status", expression = "java(com.ammdev.ofbrsrvcreateconsent.application.domain.enums.StatusEnum.AWAITING_AUTHORISATION.getValue())")
    ConsentEntity toConsentEntity(CreateConsentDataDto createConsentDataDto);
}