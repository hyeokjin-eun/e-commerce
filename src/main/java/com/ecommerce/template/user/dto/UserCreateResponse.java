package com.ecommerce.template.user.dto;

import com.ecommerce.template.user.domain.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateResponse {

    private String id;

    private String name;

    public static UserCreateResponse from(User user) {
        return UserCreateResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
