package com.ecommerce.template.user.adapter.impl;

import com.ecommerce.template.common.model.Paging;
import com.ecommerce.template.user.adapter.UserAdapter;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.domain.UserSearch;
import com.ecommerce.template.user.entity.UserEntity;
import com.ecommerce.template.user.exception.UserNotFoundException;
import com.ecommerce.template.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class UserAdapterImpl implements UserAdapter {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(UserEntity.of(user))
                .toDomain();
    }

    @Override
    public Paging<User> findByUserSearch(UserSearch userSearch) {
        String id = userSearch.getId();
        String name = userSearch.getName();
        LocalDate startDate = userSearch.getCreatedStartDate();
        LocalDate endDate = userSearch.getCreatedEndDate();
        PageRequest pageRequest = PageRequest.of(userSearch.getPage(), userSearch.getSize());
        Page<UserEntity> userEntityPage = userRepository.findByIdLikeAndNameLikeAndCreateTimeBetween(id, name, startDate, endDate, pageRequest);
        return Paging.create(
                userEntityPage.getNumber(),
                userEntityPage.getSize(),
                userEntityPage.getTotalElements(),
                userEntityPage.getContent().stream()
                        .map(UserEntity::toDomain)
                        .toList()
        );
    }

    @Override
    public User findBySeq(Long seq) {
        return userRepository.findBySeq(seq).orElseThrow(UserNotFoundException::new)
                .toDomain();
    }
}
