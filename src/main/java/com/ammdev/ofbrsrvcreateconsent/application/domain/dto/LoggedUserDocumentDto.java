package com.ammdev.ofbrsrvcreateconsent.application.domain.dto;

import lombok.Builder;

@Builder
public record LoggedUserDocumentDto(
        String id,
        String identification,
        String rel
) {
    
}
