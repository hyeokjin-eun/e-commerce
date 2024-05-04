package com.ecommerce.template.common.utils;

import com.ecommerce.template.common.utils.impl.DefaultPasswordUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("공통_비밀번호_유틸_테스트")
@Order(2)
public class DefaultPasswordUtilTest {

    private PasswordUtil passwordUtil;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.passwordUtil = new DefaultPasswordUtil(passwordEncoder);
    }

    @Test
    public void 주입된_Encoder에_따라서_비밀번호를_Encoding한다() {
        // given
        String password = "123456";

        // when
        String result = passwordUtil.encode(password);

        // then
        boolean expected = passwordEncoder.matches(password, result);
        assertThat(expected).isTrue();
    }
}
