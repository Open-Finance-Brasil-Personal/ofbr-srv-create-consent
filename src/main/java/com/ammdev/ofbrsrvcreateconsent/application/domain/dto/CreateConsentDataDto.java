package com.ammdev.ofbrsrvcreateconsent.application.domain.dto;

import java.util.Set;

import com.ammdev.ofbrsrvcreateconsent.application.domain.enums.PermissionsEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateConsentDataDto(
    @NotNull
    @Valid
    LoggedUserDto loggedUser,

    @Valid
    BusinessEntityDto businessEntity,

    @NotNull
    @Valid
    Set<PermissionsEnum> permissions,

    String expirationDateTime
) {
    
}
