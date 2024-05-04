package com.ecommerce.template.user.facade;

import com.ecommerce.template.common.utils.PasswordUtil;
import com.ecommerce.template.common.utils.TimeUtil;
import com.ecommerce.template.user.facade.impl.UserFacadeImpl;
import com.ecommerce.template.user.domain.UserCreate;
import com.ecommerce.template.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("사용자_파사드_테스트")
public class UserFacadeTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordUtil passwordUtil;

    @Mock
    private TimeUtil timeUtil;

    @InjectMocks
    private UserFacadeImpl userFacade;

    @Test
    public void 사용자를_등록한다() {
        // given
        UserCreate userCreate = UserCreate.builder()
                .id("test")
                .password("123456")
                .name("tester")
                .build();

        // when
        userFacade.create(userCreate);

        // then
        verify(passwordUtil).encode(anyString());
        verify(timeUtil).getCurrentTime();
        verify(userService).create(any());
    }
}
