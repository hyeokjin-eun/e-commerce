package com.ecommerce.template.user.dto.response;

import com.ecommerce.template.common.model.Paging;
import com.ecommerce.template.user.domain.User;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSearchResponse {

    private Integer page;

    private Integer size;

    private Long total;

    private List<UserSearchDetailResponse> list;

    public static UserSearchResponse from(Paging<User> paging) {
        List<UserSearchDetailResponse> list = paging.getList().stream()
                .map(UserSearchDetailResponse::of)
                .toList();

        return UserSearchResponse.builder()
                .page(paging.getPage())
                .size(paging.getSize())
                .total(paging.getTotal())
                .list(list)
                .build();
    }
}
