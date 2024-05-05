package com.ecommerce.template.user.controller;

import com.ecommerce.template.common.config.CommonApiTestConfig;
import com.ecommerce.template.common.enums.ResponseCode;
import com.ecommerce.template.security.config.WebConfig;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.dto.UserCreateRequest;
import com.ecommerce.template.user.facade.UserFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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
public class UserControllerTest extends CommonApiTestConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserFacade userFacade;

    @Test
    @WithMockUser
    public void 사용자_생성_API_테스트() throws Exception {
        // given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .id("test")
                .password("test")
                .name("tester")
                .build();

        LocalDateTime current = LocalDateTime.now();
        User user = User.builder()
                .seq(2L)
                .id(userCreateRequest.getId())
                .password("test")
                .createTime(current)
                .build();

        given(userFacade.create(any())).willReturn(user);

        // when, then
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userCreateRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(ResponseCode.CREATED.getCode()))
                .andExpect(jsonPath("$.message").value(ResponseCode.CREATED.getMessage()))
                .andExpect(jsonPath("$.data.seq").value(2L))
                .andExpect(jsonPath("$.data.id").value(userCreateRequest.getId()))
                .andExpect(jsonPath("$.data.name").value(userCreateRequest.getName()))
                .andDo(System.out::println);
    }

    @Test
    @WithMockUser
    public void 사용자_정보가_없을경우_API_테스트() throws Exception {
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
                        .content(objectMapper.writeValueAsBytes(userCreateRequest)))
                .andExpect(status().is5xxServerError())
                .andDo(System.out::println);
    }
}


