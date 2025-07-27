package com.ammdev.ofbrsrvcreateconsent.application.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LoggedUserDto(
    @NotNull
    @Valid
    LoggedUserDocumentDto document
) {
    
}
