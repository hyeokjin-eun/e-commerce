package com.ecommerce.template.user.exception.handler;

import com.ecommerce.template.common.dto.ResponseDto;
import com.ecommerce.template.common.enums.ResponseCode;
import com.ecommerce.template.common.utils.LoggerUtil;
import com.ecommerce.template.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
public class UserExceptionHandler {

    private final LoggerUtil loggerUtil;

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDto<Object>> handleUserNotFoundException(UserNotFoundException exception) {
        loggerUtil.printError(exception);
        return ResponseDto.error(ResponseCode.USER_NOT_FOUND);
    }
}
