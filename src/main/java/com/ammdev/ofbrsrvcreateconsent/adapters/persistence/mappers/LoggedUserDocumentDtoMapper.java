package com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ClientEntity;
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.LoggedUserDocumentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoggedUserDocumentDtoMapper {
    ClientEntity toClientEntity(LoggedUserDocumentDto loggedUserDto);
}
