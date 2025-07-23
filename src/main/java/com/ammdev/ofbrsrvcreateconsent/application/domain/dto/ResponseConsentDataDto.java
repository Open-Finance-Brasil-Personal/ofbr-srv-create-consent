package com.ammdev.ofbrsrvcreateconsent.application.domain.dto;

import java.util.Set;

import com.ammdev.ofbrsrvcreateconsent.application.domain.enums.PermissionsEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseConsentDataDto(
    @NotBlank
    String consentId,
    
    @NotBlank
    String creationDateTime,
    
    @NotBlank
    String status,
    
    @NotBlank
    String statusUpdateDateTime,
    
    @NotNull
    Set<PermissionsEnum> permissions,
    
    String expirationDateTime
) {
    
}
