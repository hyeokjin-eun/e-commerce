package com.ecommerce.template.user.controller;

import com.ecommerce.template.common.dto.ResponseDto;
import com.ecommerce.template.user.dto.UserCreateRequest;
import com.ecommerce.template.user.dto.UserCreateResponse;
import com.ecommerce.template.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping
    public ResponseDto<UserCreateResponse> create(
            @RequestBody UserCreateRequest userCreateRequest
    ) {
        return ResponseDto.success(
                UserCreateResponse.from(
                        userFacade.create(userCreateRequest.toDomain())
                )
        );
    }
}
