package com.ammdev.ofbrsrvcreateconsent.adapters.web;

import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.CreateConsentDto;
import com.ammdev.ofbrsrvcreateconsent.application.domain.dto.ResponseConsentDto;
import com.ammdev.ofbrsrvcreateconsent.application.port.incoming.CreateConsentResponseUsecase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CreateConsentController {

    private final CreateConsentResponseUsecase createConsentResponseUsecase;

    public CreateConsentController(CreateConsentResponseUsecase createConsentResponseUsecase) {
        this.createConsentResponseUsecase = createConsentResponseUsecase;
    }

    @PostMapping("/{brand}/consents")
    public ResponseConsentDto createConsent(@PathVariable String brand,
                                            @RequestBody CreateConsentDto createConsentDt,
                                            HttpServletRequest request) {
        String fullUrl = request.getRequestURL().toString();

        return this.createConsentResponseUsecase.create(createConsentDto.data(), brand, fullUrl);
    }
}
