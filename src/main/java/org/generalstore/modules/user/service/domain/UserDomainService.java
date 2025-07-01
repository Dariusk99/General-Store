package org.generalstore.modules.user.service.domain;

import org.generalstore.modules.user.entity.User;

public interface UserDomainService {

    User register(User user);

    User findUserByUsername(String username);
}
