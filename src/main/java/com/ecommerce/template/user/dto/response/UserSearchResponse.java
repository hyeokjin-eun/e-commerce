package com.ecommerce.template.user.dto.response;

import com.ecommerce.template.user.domain.UserSearch;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSearchResponse {

    private List<UserSearchDetailResponse> list;

    public static UserSearchResponse from(UserSearch userSearch) {
        List<UserSearchDetailResponse> list = userSearch.getList().stream()
                .map(UserSearchDetailResponse::of)
                .toList();

        return UserSearchResponse.builder()
                .list(list)
                .build();
    }
}
