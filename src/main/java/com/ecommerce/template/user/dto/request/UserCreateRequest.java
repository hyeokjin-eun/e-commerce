package com.ecommerce.template.user.dto.request;

import com.ecommerce.template.user.domain.UserCreate;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCreateRequest {

    @NotNull
    private String id;

    @NotNull
    private String password;

    @NotNull
    private String name;

    public UserCreate toDomain() {
        return UserCreate.builder()
                .id(this.id)
                .password(this.password)
                .name(this.name)
                .build();
    }
}


