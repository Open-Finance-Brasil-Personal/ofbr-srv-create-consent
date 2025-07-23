package com.ammdev.ofbrsrvcreateconsent.application.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record BusinessEntityDocumentDto(
    @NotBlank
    String identification,

    @NotBlank
    String rel
) {
    
}
