package com.ecommerce.template.user.service;

import com.ecommerce.template.common.model.Paging;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.domain.UserCreate;
import com.ecommerce.template.user.domain.UserSearch;

public interface UserService {

    User create(UserCreate userCreate);

    Paging<User> search(UserSearch userSearch);
}
