package com.ecommerce.template.user.service.impl;

import com.ecommerce.template.common.model.Paging;
import com.ecommerce.template.common.utils.PasswordUtil;
import com.ecommerce.template.common.utils.TimeUtil;
import com.ecommerce.template.user.adapter.UserAdapter;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.domain.UserCreate;
import com.ecommerce.template.user.domain.UserSearch;
import com.ecommerce.template.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserAdapter userAdapter;
    
    private final TimeUtil timeUtil;

    private final PasswordUtil passwordUtil;

    @Override
    public User create(UserCreate userCreate) {
        User user = User.create(userCreate, passwordUtil, timeUtil);
        return userAdapter.save(user);
    }

    @Override
    public Paging<User> search(UserSearch userSearch) {
        return userAdapter.findByUserSearch(userSearch);
    }
}
