package com.ecommerce.template.user.facade;

import com.ecommerce.template.user.domain.UserSearch;
import com.ecommerce.template.user.facade.impl.UserFacadeImpl;
import com.ecommerce.template.user.domain.UserCreate;
import com.ecommerce.template.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("사용자_파사드_테스트")
public class UserFacadeTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserFacadeImpl userFacade;

    @Test
    public void 사용자를_등록한다() throws Exception {
        // given
        UserCreate userCreate = UserCreate.builder()
                .id("test")
                .password("123456")
                .name("tester")
                .build();

        // when
        userFacade.create(userCreate);

        // then
        verify(userService).create(any());
    }

    @Test
    public void 사용자를_검색한다() {
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
        userFacade.search(userSearch);

        // then
        verify(userService).search(userSearch);
    }

    @Test
    public void 사용자_상세_정보를_조회한다() {
        // given
        Long seq = 1L;

        // when
        userFacade.detail(seq);

        // then
        verify(userService).detail(seq);
    }
}
