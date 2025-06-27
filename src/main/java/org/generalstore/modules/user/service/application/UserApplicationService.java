package org.generalstore.modules.user.service.application;

import org.generalstore.modules.user.dto.RegisterUserDTO;
import org.generalstore.modules.user.dto.UserDTO;

public interface UserApplicationService {

    UserDTO registerUser(RegisterUserDTO dto);
}
