package com.ammdev.ofbrsrvcreateconsent.application.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record BusinessEntityDocumentDto(
    @NotBlank
    String identification,

    @NotBlank
    String rel
) {
    
}
