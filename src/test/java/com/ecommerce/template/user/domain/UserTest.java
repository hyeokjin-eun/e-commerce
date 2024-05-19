package com.ecommerce.template.user.domain;

import com.ecommerce.template.common.utils.PasswordUtil;
import com.ecommerce.template.common.utils.TimeUtil;
import com.ecommerce.template.common.utils.impl.DefaultPasswordUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("사용자_도메인_테스트")
public class UserTest {

    private PasswordUtil passwordUtil;

    @Mock
    private TimeUtil timeUtil;

    private LocalDateTime currentDateTime;

    @BeforeEach
    public void setUp() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.passwordUtil = new DefaultPasswordUtil(passwordEncoder);
        this.currentDateTime = LocalDateTime.now();
    }

    @Test
    public void 사용자_생성_객체로_사용자를_생성한다() {
        // given
        UserCreate userCreate = UserCreate.builder()
                .id("test")
                .password("123456")
                .name("tester")
                .build();

        given(timeUtil.getCurrentTime()).willReturn(this.currentDateTime);

        // when
        User user = User.create(userCreate, passwordUtil, timeUtil);

        // then
        assertThat(userCreate.getId()).isEqualTo(user.getId());
        assertThat(passwordUtil.matches(userCreate.getPassword(), user.getPassword())).isTrue();
        assertThat(userCreate.getName()).isEqualTo(user.getName());
        assertThat(this.currentDateTime).isEqualTo(user.getCreateTime());
    }
}
