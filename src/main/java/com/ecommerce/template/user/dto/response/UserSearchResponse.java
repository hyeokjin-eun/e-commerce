package com.ecommerce.template.user.dto.response;

import com.ecommerce.template.user.domain.UserSearch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
