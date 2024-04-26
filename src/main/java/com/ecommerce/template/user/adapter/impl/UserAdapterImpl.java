package com.ecommerce.template.user.adapter.impl;

import com.ecommerce.template.user.adapter.UserAdapter;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.entity.UserEntity;
import com.ecommerce.template.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapterImpl implements UserAdapter {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(UserEntity.of(user))
                .toDomain();
    }
}
