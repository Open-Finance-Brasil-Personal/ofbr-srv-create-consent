package com.ammdev.ofbrsrvcreateconsent.application.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record LinksDto(
    @NotBlank
    String self
) {
    
}
