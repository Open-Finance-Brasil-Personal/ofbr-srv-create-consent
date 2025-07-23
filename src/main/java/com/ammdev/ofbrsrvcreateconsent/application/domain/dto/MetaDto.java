package com.ammdev.ofbrsrvcreateconsent.application.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record MetaDto(
    @NotBlank
    String requestDateTime
) {
    
}
