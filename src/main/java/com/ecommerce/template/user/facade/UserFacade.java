package com.ecommerce.template.user.facade;

import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.domain.UserCreate;
import com.ecommerce.template.user.domain.UserSearch;

public interface UserFacade {

    User create(UserCreate userCreate) throws Exception;

    UserSearch search(UserSearch userSearch) throws Exception;
}
