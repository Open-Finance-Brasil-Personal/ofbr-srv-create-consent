package com.ammdev.ofbrsrvcreateconsent.application.domain.enums;

public enum StatusEnum {
    AUTHORISED("AUTHORISED"),
    AWAITING_AUTHORISATION("AWAITING_AUTHORISATION"),
    REJECTED("REJECTED");

    private final String value;

    StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}