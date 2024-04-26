package com.ecommerce.template.common.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS("S0001", "Success"),
    ERROR("E0002", "Error"),
    ;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;

    private final String message;
}
