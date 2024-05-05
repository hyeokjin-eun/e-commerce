package com.ecommerce.template.user.dto.response;

import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.domain.UserSearch;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchDetailResponse {

    @NotNull
    private Long seq;

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private LocalDateTime createTime;

    public static UserSearchDetailResponse of(User user) {
        return UserSearchDetailResponse.builder()
                .seq(user.getSeq())
                .id(user.getId())
                .name(user.getName())
                .createTime(user.getCreateTime())
                .build();
    }
}
