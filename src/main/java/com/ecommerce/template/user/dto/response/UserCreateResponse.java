package com.ecommerce.template.user.dto.response;

import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.exception.UserNotFoundException;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateResponse {

    private Long seq;

    private String id;

    private String name;

    public static UserCreateResponse from(User user) {
        if (user == null) {
            throw new UserNotFoundException();
        }

        return UserCreateResponse.builder()
                .seq(user.getSeq())
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
