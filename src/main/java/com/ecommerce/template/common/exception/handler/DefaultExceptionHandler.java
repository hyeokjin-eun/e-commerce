package com.ecommerce.template.common.exception.handler;

import com.ecommerce.template.common.dto.ResponseDto;
import com.ecommerce.template.common.exception.BusinessException;
import com.ecommerce.template.common.utils.LoggerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class DefaultExceptionHandler {

    private final LoggerUtil loggerUtil;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseDto<Object>> handleBusinessException(BusinessException exception) {
        loggerUtil.printError(exception);
        return ResponseDto.error();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDto<Object>> handleRuntimeException(RuntimeException exception) {
        loggerUtil.printError(exception);
        return ResponseDto.error();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<Object>> handlerException(Exception exception) {
        loggerUtil.printError(exception);
        return ResponseDto.error();
    }
}
