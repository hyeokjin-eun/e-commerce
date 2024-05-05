package com.ecommerce.template.user.exception;

import com.ecommerce.template.common.exception.BusinessException;

public class UserNotFoundException extends BusinessException {

    private static final String MESSAGE = "user not found exception";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
