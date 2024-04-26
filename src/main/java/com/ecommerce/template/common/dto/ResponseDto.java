package com.ecommerce.template.common.dto;

import com.ecommerce.template.common.enums.ResponseCode;
import lombok.Getter;

@Getter
public class ResponseDto<T> {

    private final String code;

    private final String message;

    private final T data;

    public ResponseDto(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseDto<T> create(ResponseCode code, T data) {
        return new ResponseDto<T>(code.getCode(), code.getMessage(), data);
    }

    public static <T> ResponseDto<T> success(T data) {
        return create(ResponseCode.SUCCESS, data);
    }

    public static <T> ResponseDto<T> success() {
        return create(ResponseCode.SUCCESS, null);
    }

    public static <T> ResponseDto<T> error(ResponseCode code) {
        return create(code, null);
    }
}
