package com.ecommerce.template.user.dto;

import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.dto.response.UserDetailResponse;
import com.ecommerce.template.user.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("사용자_상세_객체_테스트")
public class UserDetailDtoTest {

    @Nested
    @DisplayName("사용자_상세_응답_객체_테스트")
    class Response {

        private static final Long seq = 1L;
        private static final String id = "test";
        private static final String password = "test";
        private static final String name = "tester";
        private static final LocalDateTime createTime = LocalDateTime.now();

        @Test
        public void 빌더로_사용자_상세_응답_생성_테스트() {
            // when
            UserDetailResponse userDetailResponse = UserDetailResponse.builder()
                    .seq(seq)
                    .name(name)
                    .createTime(createTime)
                    .build();

            // then
            assertThat(userDetailResponse.getSeq()).isEqualTo(seq);
            assertThat(userDetailResponse.getName()).isEqualTo(name);
            assertThat(userDetailResponse.getCreateTime()).isEqualTo(createTime);
        }

        @Test
        public void 도메인으로_사용자_상세_응답_생성_테스트() {
            // given
            User user = User.builder()
                    .seq(seq)
                    .id(id)
                    .password(password)
                    .name(name)
                    .createTime(createTime)
                    .build();

            // when
            UserDetailResponse userDetailResponse = UserDetailResponse.from(user);

            // then
            assertThat(userDetailResponse.getSeq()).isEqualTo(seq);
            assertThat(userDetailResponse.getName()).isEqualTo(name);
            assertThat(userDetailResponse.getCreateTime()).isEqualTo(createTime);
        }

        @Test
        public void 도메인이_없이_사용자_상세_응답_객체를_생성하려고하면_에러가_발생한다() {
            // when, then
            assertThrows(UserNotFoundException.class, () -> UserDetailResponse.from(null));
        }
    }
}
