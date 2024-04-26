package com.ecommerce.template.user.facade;

import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.domain.UserCreate;

public interface UserFacade {

    User create(UserCreate userCreate);
}
