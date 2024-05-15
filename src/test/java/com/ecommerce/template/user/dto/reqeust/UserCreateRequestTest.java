package com.ecommerce.template.user.dto.reqeust;

import com.ecommerce.template.user.domain.UserCreate;
import com.ecommerce.template.user.dto.request.UserCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("사용자_생성_요청_객체_테스트")
public class UserCreateRequestTest {

    private static final String id = "test";
    private static final String password = "test";
    private static final String name = "tester";

    @Test
    public void 생성_테스트() {
        // when
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .id(id)
                .password(password)
                .name(name)
                .build();

        // then
        assertThat(userCreateRequest.getId()).isEqualTo(id);
        assertThat(userCreateRequest.getPassword()).isEqualTo(password);
        assertThat(userCreateRequest.getName()).isEqualTo(name);
    }

    @Test
    public void 도메인_변환_테스트() {
        // given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .id(id)
                .password(password)
                .name(name)
                .build();

        // when
        UserCreate userCreate = userCreateRequest.toDomain();

        // then
        assertThat(userCreate.getId()).isEqualTo(id);
        assertThat(userCreate.getPassword()).isEqualTo(password);
        assertThat(userCreate.getName()).isEqualTo(name);
    }
}
