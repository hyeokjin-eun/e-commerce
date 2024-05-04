package com.ecommerce.template.common.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS("S0200", "Success"),
    CREATED("S0201", "Created"),
    ERROR("E0000", "Error"),
    ;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;

    private final String message;
}
