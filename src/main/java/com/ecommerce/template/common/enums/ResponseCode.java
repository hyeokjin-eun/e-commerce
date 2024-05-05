package com.ecommerce.template.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {
    SUCCESS("S0200", "Success", HttpStatus.OK),
    CREATED("S0201", "Created", HttpStatus.CREATED),
    ERROR("E0500", "Error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND("E1404", "User Not Found", HttpStatus.NOT_FOUND),
    ;

    ResponseCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    private final String code;

    private final String message;

    private final HttpStatus status;
}
