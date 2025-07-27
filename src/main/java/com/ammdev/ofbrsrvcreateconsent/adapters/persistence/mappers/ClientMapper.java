package com.ammdev.ofbrsrvcreateconsent.adapters.persistence.mappers;

import com.ammdev.ofbrsrvcreateconsent.adapters.persistence.entities.ClientEntity;
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.LoggedUserDocumentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientEntity loggedUserDocumentDtoToClientEntity(LoggedUserDocumentDto loggedUserDto);
}
