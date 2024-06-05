package com.ecommerce.template.common.exception;

public class BusinessException extends RuntimeException {

    private static final String MESSAGE = "business exception";

    public BusinessException(String message) {
        super(MESSAGE);
    }
}
