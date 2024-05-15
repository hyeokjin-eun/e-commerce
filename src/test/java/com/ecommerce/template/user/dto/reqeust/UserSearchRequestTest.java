package com.ecommerce.template.user.dto.reqeust;

import com.ecommerce.template.user.domain.UserSearch;
import com.ecommerce.template.user.dto.request.UserSearchRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("사용자_검색_요청_객체_테스트")
public class UserSearchRequestTest {

    private static final String id = "test";
    private static final String name = "tester";

    @Test
    public void 생성_테스트(){
        // when
        UserSearchRequest userSearchRequest = UserSearchRequest.builder()
                .id(id)
                .name(name)
                .build();

        // then
        assertThat(userSearchRequest.getId()).isEqualTo(id);
        assertThat(userSearchRequest.getName()).isEqualTo(name);
    }

    @Test
    public void 도메인_변환_테스트() {
        // given
        UserSearchRequest userSearchRequest = UserSearchRequest.builder()
                .id(id)
                .name(name)
                .build();

        // when
        UserSearch userSearch = userSearchRequest.toDomain();

        // then
        assertThat(userSearch.getId()).isEqualTo(id);
        assertThat(userSearch.getName()).isEqualTo(name);
    }
}
