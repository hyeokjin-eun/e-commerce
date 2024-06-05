package com.ecommerce.template.common.dto;

import com.ecommerce.template.common.enums.ResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ResponseDto<T> {

    private final String code;

    private final String message;

    private final T data;

    private ResponseDto(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private static <T> ResponseDto<T> generateResponseDto(String code, String message, T data) {
        return new ResponseDto<>(code, message, data);
    }

    private static <T> ResponseEntity<ResponseDto<T>> generate(HttpStatus status, String code, String message, T data) {
        return ResponseEntity.status(status)
                .body(generateResponseDto(code, message, data));
    }

    private static <T> ResponseEntity<ResponseDto<T>> generate(ResponseCode responseCode, T data) {
        return generate(responseCode.getStatus(), responseCode.getCode(), responseCode.getMessage(), data);
    }

    public static <T> ResponseEntity<ResponseDto<T>> created(T data) {
        return generate(ResponseCode.CREATED, data);
    }

    public static <T> ResponseEntity<ResponseDto<T>> success(T data) {
        return generate(ResponseCode.SUCCESS, data);
    }

    public static <T> ResponseEntity<ResponseDto<T>> success() {
        return success(null);
    }

    public static <T> ResponseEntity<ResponseDto<T>> badRequest() {
        return generate(ResponseCode.BAD_REQUEST, null);
    }

    public static <T> ResponseEntity<ResponseDto<T>> error() {
        return generate(ResponseCode.ERROR, null);
    }

    public static <T> ResponseEntity<ResponseDto<T>> error(ResponseCode responseCode) {
        return generate(responseCode, null);
    }

    public static <T> ResponseEntity<ResponseDto<T>> notFound() {
        return generate(ResponseCode.NOT_FOUND, null);
    }
}
