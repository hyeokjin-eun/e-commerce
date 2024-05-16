package com.ecommerce.template.user.controller.userController;

import com.ecommerce.template.common.config.ApiTestConfig;
import com.ecommerce.template.common.enums.ResponseCode;
import com.ecommerce.template.common.utils.TimeUtil;
import com.ecommerce.template.user.controller.UserController;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.domain.UserSearch;
import com.ecommerce.template.user.facade.UserFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("사용자_검색_API_테스트")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class UserSearchTest extends UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TimeUtil timeUtil;

    @MockBean
    private UserFacade userFacade;

    @Nested
    @DisplayName("성공(200)")
    @Order(1)
    class Status200 {

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
    }

    @Nested
    @DisplayName("실패(400)")
    @Order(2)
    class Status400 {

        @Test
        public void 생성_시작_일자가_포멧에_맞지_않으면_400_상태를_반환한다() throws Exception {
            // given
            String id = "test";
            String name = "tester";
            String createdStartDate = "20240501";
            LocalDate createdEndDate = timeUtil.ofLocalDate(2024, 5, 6);

            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
            multiValueMap.add("id", id);
            multiValueMap.add("name", name);
            multiValueMap.add("createdStartDate", createdStartDate);
            multiValueMap.add("createdEndDate", createdEndDate.toString());

            // when, then
            mockMvc.perform(get("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .params(multiValueMap))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(ResponseCode.BAD_REQUEST.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.BAD_REQUEST.getMessage()));
        }

        @Test
        public void 생성_종료_일자가_포멧에_맞지_않으면_400_상태를_반환한다() throws Exception {
            // given
            String id = "test";
            String name = "tester";
            LocalDate createdStartDate = timeUtil.ofLocalDate(2024, 5, 1);
            String createdEndDate = "20240506";

            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
            multiValueMap.add("id", id);
            multiValueMap.add("name", name);
            multiValueMap.add("createdStartDate", createdStartDate.toString());
            multiValueMap.add("createdEndDate", createdEndDate);

            // when, then
            mockMvc.perform(get("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .params(multiValueMap))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(ResponseCode.BAD_REQUEST.getCode()))
                    .andExpect(jsonPath("$.message").value(ResponseCode.BAD_REQUEST.getMessage()));
        }
    }

    @Nested
    @DisplayName("실패(500)")
    @Order(3)
    class Status500 {

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
