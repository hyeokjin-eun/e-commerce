package com.ecommerce.template.user.entity;

import com.ecommerce.template.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("사용자_엔티티_테스트")
public class UserEntityTest {

    @Test
    public void 사용자로_사용자_엔티티로_변환한다() {
        LocalDateTime current = LocalDateTime.now();
        User user = User.builder()
                .seq(1L)
                .id("test")
                .password("123456")
                .name("tester")
                .createTime(current)
                .build();

        UserEntity userEntity = UserEntity.of(user);

        assertThat(user.getId()).isEqualTo(userEntity.getId());
        assertThat(user.getName()).isEqualTo(userEntity.getName());
        assertThat(user.getPassword()).isEqualTo(userEntity.getPassword());
        assertThat(user.getCreateTime()).isEqualTo(current);
    }
}
