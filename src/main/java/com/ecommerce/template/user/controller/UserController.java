package com.ecommerce.template.user.controller;

import com.ecommerce.template.common.dto.ResponseDto;
import com.ecommerce.template.user.dto.request.UserCreateRequest;
import com.ecommerce.template.user.dto.request.UserSearchRequest;
import com.ecommerce.template.user.dto.response.UserCreateResponse;
import com.ecommerce.template.user.dto.response.UserDetailResponse;
import com.ecommerce.template.user.dto.response.UserSearchResponse;
import com.ecommerce.template.user.facade.UserFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping
    public ResponseEntity<ResponseDto<UserCreateResponse>> create(@Valid @RequestBody UserCreateRequest userCreateRequest) throws Exception {
        return ResponseDto.created(UserCreateResponse.from(userFacade.create(userCreateRequest.toDomain())));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<UserSearchResponse>> search(UserSearchRequest userSearchRequest) throws Exception {
        return ResponseDto.success(UserSearchResponse.from(userFacade.search(userSearchRequest.toDomain())));
    }

    @GetMapping("{seq}")
    public ResponseEntity<ResponseDto<UserDetailResponse>> details(@PathVariable Long seq) throws Exception {
        return ResponseDto.success(UserDetailResponse.from(userFacade.detail(seq)));
    }
}
