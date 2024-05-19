package com.ecommerce.template.user.adapter;

import com.ecommerce.template.common.model.Paging;
import com.ecommerce.template.user.adapter.impl.UserAdapterImpl;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.domain.UserSearch;
import com.ecommerce.template.user.entity.UserEntity;
import com.ecommerce.template.user.entity.UserEntityTest;
import com.ecommerce.template.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("사용자_어뎁터_테스트")
public class UserAdapterTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserAdapterImpl userAdapter;

    @Nested
    @DisplayName("userAdapter.save_테스트")
    class Save {

        @Test
        public void 사용자_객체를_사용해_사용자를_생성한다() {
            // given
            User user = User.builder()
                    .seq(Long.valueOf(String.valueOf(1L)))
                    .id("test")
                    .password("test")
                    .name("tester")
                    .createTime(LocalDateTime.now())
                    .build();

            UserEntity userEntity = UserEntity.builder()
                    .seq(Long.valueOf(String.valueOf(1L)))
                    .id("test")
                    .password("test")
                    .name("tester")
                    .createTime(LocalDateTime.now())
                    .build();

            given(userRepository.save(any(UserEntity.class))).willReturn(userEntity);

            // when
            userAdapter.save(user);

            // then
            verify(userRepository).save(any(UserEntity.class));
        }
    }

    @Nested
    @DisplayName("userAdapter.findUserSearch_테스트")
    class FindUserSearch {

        @Test
        public void 사용자_검색_객체를_사용해_사용자를_검색한다() {
            // given
            int page = 0;
            int size = 10;
            String id = "test";
            String name = "tester";
            LocalDate startDate = LocalDate.of(2024, 5, 20);
            LocalDate endDate = LocalDate.of(2024, 5, 24);

            UserSearch userSearch = UserSearch.builder()
                    .page(page)
                    .size(size)
                    .id(id)
                    .name(name)
                    .createdStartDate(startDate)
                    .createdEndDate(endDate)
                    .build();

            List<UserEntity> userEntityList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                UserEntity userEntity = UserEntity.builder()
                        .seq(Long.valueOf(String.valueOf(i)))
                        .id("test" + i)
                        .name("tester" + i)
                        .createTime(LocalDateTime.now())
                        .build();

                userEntityList.add(userEntity);
            }

            Page<UserEntity> userEntityPage = new PageImpl<>(userEntityList, PageRequest.of(page, size), userEntityList.size());
            given(userRepository.findByIdLikeAndNameLikeAndCreateTimeBetween(id, name, startDate, endDate, PageRequest.of(page, size))).willReturn(userEntityPage);

            // when
            Paging<User> userPaging = userAdapter.findByUserSearch(userSearch);

            // then
            verify(userRepository).findByIdLikeAndNameLikeAndCreateTimeBetween(id, name, startDate, endDate, PageRequest.of(page, size));
            assertThat(userPaging.getPage()).isEqualTo(page);
            assertThat(userPaging.getSize()).isEqualTo(size);
            assertThat(userPaging.getTotal()).isEqualTo(userEntityList.size());
            for (int i = 0; i < 10; i++) {
                UserEntity userEntity = userEntityList.get(i);
                User user = userPaging.getList().get(i);
                assertThat(user.getSeq()).isEqualTo(userEntity.getSeq());
                assertThat(user.getId()).isEqualTo(userEntity.getId());
                assertThat(user.getName()).isEqualTo(userEntity.getName());
                assertThat(user.getPassword()).isEqualTo(userEntity.getPassword());
                assertThat(user.getCreateTime()).isEqualTo(userEntity.getCreateTime());
            }
        }
    }
}
