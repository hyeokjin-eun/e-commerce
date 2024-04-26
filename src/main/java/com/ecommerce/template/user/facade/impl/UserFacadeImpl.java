package com.ecommerce.template.user.facade.impl;

import com.ecommerce.template.common.utils.PasswordUtil;
import com.ecommerce.template.common.utils.TimeUtil;
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

    private final PasswordUtil passwordUtil;

    private final TimeUtil timeUtil;

    @Override
    public User create(UserCreate userCreate) {
        User user = User.create(userCreate, passwordUtil, timeUtil);
        return userService.create(user);
    }
}
