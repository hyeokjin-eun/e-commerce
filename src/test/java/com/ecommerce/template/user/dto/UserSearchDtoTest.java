package com.ecommerce.template.user.dto;

import com.ecommerce.template.common.model.Paging;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.domain.UserSearch;
import com.ecommerce.template.user.dto.request.UserSearchRequest;
import com.ecommerce.template.user.dto.response.UserSearchDetailResponse;
import com.ecommerce.template.user.dto.response.UserSearchResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("사용자_검색_객체_테스트")
public class UserSearchDtoTest {

    @Nested
    @DisplayName("사용자_검색_요청_객체_테스트")
    class Request {
        private static final String id = "test";
        private static final String name = "tester";

        @Test
        public void 빌더로_사용자_검색_요청_생성_테스트(){
            // when
            UserSearchRequest userSearchRequest = UserSearchRequest.builder()
                    .id(id)
                    .name(name)
                    .build();

            // then
            assertThat(userSearchRequest.getId()).isEqualTo(id);
            assertThat(userSearchRequest.getName()).isEqualTo(name);
        }

        @Test
        public void 도메인으로_사용자_검색_요청_생성_테스트() {
            // given
            UserSearchRequest userSearchRequest = UserSearchRequest.builder()
                    .id(id)
                    .name(name)
                    .build();

            // when
            UserSearch userSearch = userSearchRequest.toDomain();

            // then
            assertThat(userSearch.getId()).isEqualTo(id);
            assertThat(userSearch.getName()).isEqualTo(name);
        }
    }

    @Nested
    @DisplayName("사용자_검색_응답_객체_테스트")
    class Response {
        private static final Integer page = 0;
        private static final Integer size = 10;
        private static final Long total = 10L;
        private List<UserSearchDetailResponse> users;

        @BeforeEach
        public void setUp() {
            List<UserSearchDetailResponse> users = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                UserSearchDetailResponse userSearchDetailResponse =UserSearchDetailResponse.builder()
                        .seq(Long.valueOf(String.valueOf(i)))
                        .id("test" + i)
                        .name("tester" + i)
                        .createTime(LocalDateTime.now())
                        .build();

                users.add(userSearchDetailResponse);
            }

            this.users = users;
        }

        @Test
        public void 빌더로_사용자_검색_응답_생성_테스트() {
            // when
            UserSearchResponse userSearchResponse = UserSearchResponse.builder()
                    .page(page)
                    .size(size)
                    .total(total)
                    .list(users)
                    .build();

            // then
            assertThat(userSearchResponse.getPage()).isEqualTo(page);
            assertThat(userSearchResponse.getSize()).isEqualTo(size);
            assertThat(userSearchResponse.getTotal()).isEqualTo(total);
            for (int i = 0; i < 10; i++) {
                UserSearchDetailResponse userSearchDetailResponse = userSearchResponse.getList().get(i);
                assertThat(userSearchDetailResponse.getSeq()).isEqualTo(Long.valueOf(String.valueOf(i)));
                assertThat(userSearchDetailResponse.getId()).isEqualTo("test" + i);
                assertThat(userSearchDetailResponse.getName()).isEqualTo("tester" + i);
            }
        }

        @Test
        public void 도메인으로_사용자_검색_응답_생성_테스트() {
            // given
            List<User> userList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                User user = User.builder()
                        .seq(Long.valueOf(String.valueOf(i)))
                        .id("test" + i)
                        .password("test" + i)
                        .name("tester" + i)
                        .createTime(LocalDateTime.now())
                        .build();

                userList.add(user);
            }

            Paging<User> paging = Paging.create(page, size, total, userList);

            // when
            UserSearchResponse userSearchResponse = UserSearchResponse.from(paging);

            // then
            assertThat(userSearchResponse.getPage()).isEqualTo(page);
            assertThat(userSearchResponse.getSize()).isEqualTo(size);
            assertThat(userSearchResponse.getTotal()).isEqualTo(total);
            for (int i = 0; i < 10; i++) {
                UserSearchDetailResponse userSearchDetailResponse = userSearchResponse.getList().get(i);
                User user = userList.get(i);
                assertThat(userSearchDetailResponse.getSeq()).isEqualTo(user.getSeq());
                assertThat(userSearchDetailResponse.getId()).isEqualTo(user.getId());
                assertThat(userSearchDetailResponse.getName()).isEqualTo(user.getName());
                assertThat(userSearchDetailResponse.getCreateTime()).isEqualTo(user.getCreateTime());
            }
        }
    }
}
