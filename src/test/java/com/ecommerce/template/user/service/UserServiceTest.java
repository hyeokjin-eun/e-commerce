package com.ecommerce.template.user.service;

import com.ecommerce.template.user.adapter.UserAdapter;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("사용자_서비스_테스트")
public class UserServiceTest {

    @Mock
    private UserAdapter userAdapter;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void User객체를_사용해_사용자를_생성한다() {
        // given
        User user = User.builder()
                .id("test")
                .password("123456")
                .name("tester")
                .createTime(LocalDateTime.now())
                .build();

        // when
        userService.create(user);

        // then
        verify(userAdapter).save(user);
    }
}
