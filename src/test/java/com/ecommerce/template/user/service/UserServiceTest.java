package com.ecommerce.template.user.service;

import com.ecommerce.template.common.utils.PasswordUtil;
import com.ecommerce.template.common.utils.TimeUtil;
import com.ecommerce.template.user.adapter.UserAdapter;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.domain.UserCreate;
import com.ecommerce.template.user.domain.UserSearch;
import com.ecommerce.template.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("사용자_서비스_테스트")
public class UserServiceTest {

    @Mock
    private UserAdapter userAdapter;

    @Mock
    private PasswordUtil passwordUtil;

    @Mock
    private TimeUtil timeUtil;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void 사용자_생성_객체를_사용해_사용자를_생성한다() {
        // given
        UserCreate userCreate = UserCreate.builder()
                .id("test")
                .password("123456")
                .name("tester")
                .build();

        // when
        userService.create(userCreate);

        // then
        verify(passwordUtil).encode(anyString());
        verify(timeUtil).getCurrentTime();
        verify(userAdapter).save(any(User.class));
    }

    @Test
    public void 사용자_검색_객체를_사용해_사용자를_검색한다() {
        // given
        UserSearch userSearch = UserSearch.builder()
                .page(0)
                .size(30)
                .id("test")
                .name("tester")
                .createdStartDate(LocalDate.of(2024, 5, 20))
                .createdEndDate(LocalDate.of(2024, 5, 24))
                .build();

        // when
        userService.search(userSearch);

        // then
        verify(userAdapter).findByUserSearch(userSearch);
    }
}
