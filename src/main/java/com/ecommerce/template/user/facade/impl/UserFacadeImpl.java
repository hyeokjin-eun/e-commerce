package com.ecommerce.template.user.facade.impl;

import com.ecommerce.template.common.model.Paging;
import com.ecommerce.template.user.domain.UserSearch;
import com.ecommerce.template.user.facade.UserFacade;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.domain.UserCreate;
import com.ecommerce.template.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    @Override
    public User create(UserCreate userCreate) throws Exception {
        return userService.create(userCreate);
    }

    @Override
    public Paging<User> search(UserSearch userSearch) {
        return userService.search(userSearch);
    }

    @Override
    public User detail(Long seq) {
        return userService.detail(seq);
    }
}
