package com.ammdev.ofbrsrvcreateconsent.application.domain.dto;

import jakarta.validation.constraints.NotNull;

public record CreateConsentDto(
    @NotNull
    CreateConsentDataDto data
) {
    
}
