package com.ecommerce.template.user.controller;

import com.ecommerce.template.common.dto.ResponseDto;
import com.ecommerce.template.user.dto.UserCreateRequest;
import com.ecommerce.template.user.dto.UserCreateResponse;
import com.ecommerce.template.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto<UserCreateResponse>> create(
            @RequestBody UserCreateRequest userCreateRequest
    ) {
        return ResponseDto.success(
                UserCreateResponse.from(
                        userFacade.create(userCreateRequest.toDomain())
                )
        );
    }
}
