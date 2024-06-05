package com.ecommerce.template.user.controller.userController;

import com.ecommerce.template.common.enums.ResponseCode;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.facade.UserFacade;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("사용자_상세_API_테스트")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class UserDetailTest extends UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFacade userFacade;

    @Nested
    @DisplayName("성공(200)")
    @Order(1)
    class Status200 {

        @Test
        public void 사용자_상세조회에_성공하면_200_상태를_반환한다() throws Exception {
            // given
            Long userSeq = 1L;
            String id = "test";
            String password = "test";
            String name = "tester";
            LocalDateTime current = LocalDateTime.now();

            User user = User.builder()
                    .seq(userSeq)
                    .id(id)
                    .password(password)
                    .name(name)
                    .createTime(current)
                    .build();

            given(userFacade.detail(userSeq)).willReturn(user);

            // when, then
            mockMvc.perform(get("/api/users/" + userSeq))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(ResponseCode.SUCCESS.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.SUCCESS.getMessage()))
                    .andExpect(jsonPath("$.data.seq").value(userSeq))
                    .andExpect(jsonPath("$.data.name").value(name))
                    .andExpect(jsonPath("$.data.createTime").value(current.toString()))
                    ;
        }
    }

    @Nested
    @DisplayName("실패(404)")
    @Order(2)
    class Status404 {

        @Test
        public void 사용자_상세조회에서_사용자를_찾을수_없다면_404_상태를_반환한다() throws Exception {
            // given
            Long userSeq = 1L;

            given(userFacade.detail(userSeq)).willReturn(null);

            // when, then
            mockMvc.perform(get("/api/users/" + userSeq))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.code").value(ResponseCode.USER_NOT_FOUND.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.USER_NOT_FOUND.getMessage()))
            ;
        }
    }

    @Nested
    @DisplayName("실패(500)")
    @Order(3)
    class Status500 {

        @Test
        public void 사용자_상세조회에서_런타임_오류가_발생하면_500_상태를_반환한다() throws Exception {
            // given
            Long userSeq = 1L;

            given(userFacade.detail(userSeq)).willThrow(new RuntimeException());

            // when, then
            mockMvc.perform(get("/api/users/" + userSeq))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("$.code").value(ResponseCode.ERROR.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.ERROR.getMessage()))
            ;
        }

        @Test
        public void 사용자_상세조회에서_오류가_발생하면_500_상태를_반환한다() throws Exception {
            // given
            Long userSeq = 1L;

            given(userFacade.detail(userSeq)).willThrow(new Exception());

            // when, then
            mockMvc.perform(get("/api/users/" + userSeq))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("$.code").value(ResponseCode.ERROR.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.ERROR.getMessage()))
            ;
        }
    }
}
