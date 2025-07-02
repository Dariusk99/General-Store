package org.generalstore.modules.user.factory;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.user.dto.RegisterUserDTO;
import org.generalstore.modules.user.entity.Role;
import org.generalstore.modules.user.entity.User;
import org.generalstore.modules.user.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public User createUserFromDTO(RegisterUserDTO registerUserDTO) {
        User user = userMapper.toRegisterEntity(registerUserDTO);
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setRoles(Set.of(Role.ROLE_USER));
        return user;
    }
}
