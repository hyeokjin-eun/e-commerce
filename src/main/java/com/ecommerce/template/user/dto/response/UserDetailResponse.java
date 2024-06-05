package com.ecommerce.template.user.dto.response;

import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.exception.UserNotFoundException;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDetailResponse {

    private Long seq;

    private String name;

    private LocalDateTime createTime;

    public static UserDetailResponse from(User user) {
        if (user == null) {
            throw new UserNotFoundException();
        }

        return UserDetailResponse.builder()
                .seq(user.getSeq())
                .name(user.getName())
                .createTime(user.getCreateTime())
                .build();
    }
}
