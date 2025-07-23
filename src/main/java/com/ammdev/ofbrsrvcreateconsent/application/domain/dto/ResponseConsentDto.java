package com.ammdev.ofbrsrvcreateconsent.application.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ResponseConsentDto(
    @NotNull
    @Valid
    ResponseConsentDataDto data,
    LinksDto links,
    MetaDto meta
) {
    
}
