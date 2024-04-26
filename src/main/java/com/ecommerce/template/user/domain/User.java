package com.ecommerce.template.user.domain;

import com.ecommerce.template.common.utils.PasswordUtil;
import com.ecommerce.template.common.utils.TimeUtil;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long seq;

    private String id;

    private String password;

    private String name;

    private LocalDateTime createTime;

    public static User create(UserCreate userCreate, PasswordUtil passwordUtil, TimeUtil timeUtil) {
        return User.builder()
                .id(userCreate.getId())
                .password(passwordUtil.encode(userCreate.getPassword()))
                .name(userCreate.getName())
                .createTime(timeUtil.getCurrentTime())
                .build();
    }
}
