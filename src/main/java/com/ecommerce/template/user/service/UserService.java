package com.ecommerce.template.user.service;

import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.domain.UserCreate;

public interface UserService {

    User create(UserCreate userCreate);
}
