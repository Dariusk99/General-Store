package org.generalstore.modules.auth.service;

import org.generalstore.modules.user.dto.UserLoginDTO;

public interface AuthService {
    String login(UserLoginDTO dto);
}