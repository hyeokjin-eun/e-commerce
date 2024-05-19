package com.ecommerce.template.user.adapter;

import com.ecommerce.template.common.model.Paging;
import com.ecommerce.template.user.domain.User;
import com.ecommerce.template.user.domain.UserSearch;

public interface UserAdapter {

    User save(User user);

    Paging<User> findByUserSearch(UserSearch userSearch);
}
