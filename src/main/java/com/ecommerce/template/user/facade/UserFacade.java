package com.ecommerce.template.user.facade;

import com.ecommerce.template.common.model.Paging;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.domain.UserCreate;
import com.ecommerce.template.user.domain.UserSearch;
import org.springframework.data.domain.Page;

public interface UserFacade {

    User create(UserCreate userCreate) throws Exception;

    Paging<User> search(UserSearch userSearch) throws Exception;

    User detail(Long seq) throws Exception;
}
