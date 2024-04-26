package com.ecommerce.template.user.dto;

import com.ecommerce.template.user.domain.UserCreate;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    private String id;

    private String password;

    private String name;

    public UserCreate toDomain() {
        return UserCreate.builder()
                .id(this.id)
                .password(this.password)
                .name(this.name)
                .build();
    }
}
