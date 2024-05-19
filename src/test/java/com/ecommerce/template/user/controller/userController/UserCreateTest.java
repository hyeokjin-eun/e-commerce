package com.ecommerce.template.user.controller.userController;

import com.ecommerce.template.common.enums.ResponseCode;
import com.ecommerce.template.common.utils.TimeUtil;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.dto.request.UserCreateRequest;
import com.ecommerce.template.user.exception.UserNotFoundException;
import com.ecommerce.template.user.facade.UserFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("사용자_생성_API_테스트")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class UserCreateTest extends UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TimeUtil timeUtil;

    @MockBean
    private UserFacade userFacade;

    @Nested
    @DisplayName("성공(201)")
    @Order(1)
    class Status201 {

        @Test
        public void 사용자_생성에_성공하면_201_상태를_반환한다() throws Exception {
            // given
            UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                    .id("test")
                    .password("test")
                    .name("tester")
                    .build();

            LocalDateTime current = LocalDateTime.now();
            User user = User.builder()
                    .seq(1L)
                    .id(userCreateRequest.getId())
                    .password("test")
                    .name("tester")
                    .createTime(current)
                    .build();

            given(userFacade.create(any())).willReturn(user);

            // when, then
            mockMvc.perform(post("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userCreateRequest)))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.code").value(ResponseCode.CREATED.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.CREATED.getMessage()))
                    .andExpect(jsonPath("$.data.seq").value(1L))
                    .andExpect(jsonPath("$.data.id").value(userCreateRequest.getId()))
                    .andExpect(jsonPath("$.data.name").value(userCreateRequest.getName()));
        }
    }

    @Nested
    @DisplayName("실패(400)")
    @Order(2)
    class Status400 {

        @Test
        public void 사용자_Id가_없을경우_400_상태를_반환한다() throws Exception {
            // given
            UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                    .id(null)
                    .password("test")
                    .name("tester")
                    .build();

            // when, then
            mockMvc.perform(post("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userCreateRequest)))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(ResponseCode.BAD_REQUEST.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.BAD_REQUEST.getMessage()));
        }

        @Test
        public void 사용자_Password가_없을경우_400_상태를_반환한다() throws Exception {
            // given
            UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                    .id("test")
                    .password(null)
                    .name("tester")
                    .build();

            // when, then
            mockMvc.perform(post("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userCreateRequest)))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(ResponseCode.BAD_REQUEST.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.BAD_REQUEST.getMessage()));
        }

        @Test
        public void 사용자_Name이_없을경우_400_상태를_반환한다() throws Exception {
            // given
            UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                    .id("test")
                    .password("test")
                    .name(null)
                    .build();

            // when, then
            mockMvc.perform(post("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userCreateRequest)))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(ResponseCode.BAD_REQUEST.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.BAD_REQUEST.getMessage()));
        }
    }

    @Nested
    @DisplayName("실패(404)")
    @Order(3)
    class Status404 {
        @Test
        public void 사용자_정보가_없어서_응답_모델_생성에_실패하면_404_상태를_반환한다() throws Exception {
            // given
            UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                    .id("test")
                    .password("test")
                    .name("tester")
                    .build();

            given(userFacade.create(any())).willReturn(null);

            // when, then
            mockMvc.perform(post("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userCreateRequest)))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.code").value(ResponseCode.USER_NOT_FOUND.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.USER_NOT_FOUND.getMessage()));
        }

        @Test
        public void 사용자_정보가_없을_경우_404_상태를_반환한다() throws Exception {
            // given
            UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                    .id("test")
                    .password("test")
                    .name("tester")
                    .build();

            given(userFacade.create(any())).willThrow(new UserNotFoundException());

            // when, then
            mockMvc.perform(post("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userCreateRequest)))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.code").value(ResponseCode.USER_NOT_FOUND.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.USER_NOT_FOUND.getMessage()));
        }
    }

    @Nested
    @DisplayName("실패(500)")
    @Order(4)
    class Status500 {

        @Test
        public void 사용자를_생성하던_중에_런타임_오류가_발생하면_500_상태를_반환한다() throws Exception {
            // given
            UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                    .id("test")
                    .password("test")
                    .name("tester")
                    .build();

            given(userFacade.create(any())).willThrow(new RuntimeException());

            // when, then
            mockMvc.perform(post("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userCreateRequest)))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("$.code").value(ResponseCode.ERROR.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.ERROR.getMessage()));
        }

        @Test
        public void 사용자를_생성하던_중에_오류가_발생하면_500_상태를_반환한다() throws Exception {
            // given
            UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                    .id("test")
                    .password("test")
                    .name("tester")
                    .build();

            given(userFacade.create(any())).willThrow(new Exception());

            // when, then
            mockMvc.perform(post("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userCreateRequest)))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("$.code").value(ResponseCode.ERROR.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.ERROR.getMessage()));
        }
    }
}
