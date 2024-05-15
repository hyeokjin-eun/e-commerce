package com.ecommerce.template.user.dto.response;

import com.ecommerce.template.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("사용자_생성_응답_객체_테스트")
public class UserCreateResponseTest {

    private static final Long seq = 1L;
    private static final String id = "test";
    private static final String password = "test";
    private static final String name = "tester";
    private static final LocalDateTime createTime = LocalDateTime.now();

    @Test
    public void 빌더_생성_테스트() {
        // when
        UserCreateResponse userCreateResponse = UserCreateResponse.builder()
                .seq(seq)
                .id(id)
                .name(name)
                .build();

        // then
        assertThat(userCreateResponse.getSeq()).isEqualTo(seq);
        assertThat(userCreateResponse.getId()).isEqualTo(id);
        assertThat(userCreateResponse.getName()).isEqualTo(name);
    }

    @Test
    public void 도메인_으로_객체_생성_테스트() {
        // given
        User user = User.builder()
                .seq(seq)
                .id(id)
                .password(password)
                .name(name)
                .createTime(createTime)
                .build();

        // when
        UserCreateResponse userCreateResponse = UserCreateResponse.from(user);

        // then
        assertThat(userCreateResponse.getSeq()).isEqualTo(user.getSeq());
        assertThat(userCreateResponse.getId()).isEqualTo(user.getId());
        assertThat(userCreateResponse.getName()).isEqualTo(user.getName());
    }
}
