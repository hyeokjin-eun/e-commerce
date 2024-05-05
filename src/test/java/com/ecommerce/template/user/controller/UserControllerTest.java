package com.ecommerce.template.user.controller;

import com.ecommerce.template.common.config.CommonApiTestConfig;
import com.ecommerce.template.common.enums.ResponseCode;
import com.ecommerce.template.common.utils.TimeUtil;
import com.ecommerce.template.common.utils.impl.DefaultTimeUtil;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.domain.UserSearch;
import com.ecommerce.template.user.dto.request.UserCreateRequest;
import com.ecommerce.template.user.exception.UserNotFoundException;
import com.ecommerce.template.user.facade.UserFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = UserController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
@DisplayName("사용자_API_테스트")
@Import(DefaultTimeUtil.class)
public class UserControllerTest extends CommonApiTestConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TimeUtil timeUtil;

    @MockBean
    private UserFacade userFacade;

    @Nested
    @DisplayName("사용자_생성_API_테스트")
    class Create {

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
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.code").value(ResponseCode.CREATED.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.CREATED.getMessage()))
                    .andExpect(jsonPath("$.data.seq").value(1L))
                    .andExpect(jsonPath("$.data.id").value(userCreateRequest.getId()))
                    .andExpect(jsonPath("$.data.name").value(userCreateRequest.getName()));
        }

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
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.code").value(ResponseCode.USER_NOT_FOUND.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.USER_NOT_FOUND.getMessage()));
        }

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
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("$.code").value(ResponseCode.ERROR.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.ERROR.getMessage()));
        }
    }

    @Nested
    @DisplayName("사용자_검색_API_테스트")
    class Search {

        @Test
        public void 사용자_검색에_성공하면_200_상태를_반환한다() throws Exception {
            // given
            String id = "test";
            String name = "tester";
            LocalDate createdStartDate = timeUtil.ofLocalDate(2024, 5, 1);
            LocalDate createdEndDate =  timeUtil.ofLocalDate(2024, 5, 6);

            LocalDate baseDate = timeUtil.ofLocalDate(2024, 5, 1);
            List<User> userList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                User user = User.builder()
                        .seq(Long.parseLong(String.valueOf(i)))
                        .id("test" + i)
                        .name("tester" + i)
                        .createTime(baseDate.plusDays(i).atStartOfDay())
                        .build();

                userList.add(user);
            }

            UserSearch userSearch = UserSearch.builder()
                    .id(id)
                    .name(name)
                    .createdStartDate(createdStartDate)
                    .createdEndDate(createdEndDate)
                    .list(userList)
                    .build();

            given(userFacade.search(any())).willReturn(userSearch);

            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
            multiValueMap.add("id", id);
            multiValueMap.add("name", name);
            multiValueMap.add("createdStartDate", createdStartDate.toString());
            multiValueMap.add("createdEndDate", createdEndDate.toString());

            // when, then
            mockMvc.perform(get("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .params(multiValueMap))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(ResponseCode.SUCCESS.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.SUCCESS.getMessage()));
        }

        @Test
        public void 사용자_검색에서_런타임_오류가_발생하면_500_상태를_반환한다() throws Exception {
            // given
            String id = "test";
            String name = "tester";
            LocalDate createdStartDate = timeUtil.ofLocalDate(2024, 5, 1);
            LocalDate createdEndDate = timeUtil.ofLocalDate(2024, 5, 6);

            given(userFacade.search(any())).willThrow(new RuntimeException());

            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
            multiValueMap.add("id", id);
            multiValueMap.add("name", name);
            multiValueMap.add("createdStartDate", createdStartDate.toString());
            multiValueMap.add("createdEndDate", createdEndDate.toString());

            // when, then
            mockMvc.perform(get("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .params(multiValueMap))
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("$.code").value(ResponseCode.ERROR.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.ERROR.getMessage()));
        }

        @Test
        public void 사용자_검색에서_오류가_발생하면_500_상태를_반환한다() throws Exception {
            // given
            String id = "test";
            String name = "tester";
            LocalDate createdStartDate = timeUtil.ofLocalDate(2024, 5, 1);
            LocalDate createdEndDate = timeUtil.ofLocalDate(2024, 5, 6);

            given(userFacade.search(any())).willThrow(new Exception());

            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
            multiValueMap.add("id", id);
            multiValueMap.add("name", name);
            multiValueMap.add("createdStartDate", createdStartDate.toString());
            multiValueMap.add("createdEndDate", createdEndDate.toString());

            // when, then
            mockMvc.perform(get("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .params(multiValueMap))
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("$.code").value(ResponseCode.ERROR.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.ERROR.getMessage()));
        }
    }
}


