package org.generalstore.modules.user.service.application;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.user.dto.RegisterUserDTO;
import org.generalstore.modules.user.dto.UserDTO;
import org.generalstore.modules.user.entity.Role;
import org.generalstore.modules.user.entity.User;
import org.generalstore.modules.user.mapper.UserMapper;
import org.generalstore.modules.user.service.domain.UserDomainService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
class UserApplicationServiceImpl implements UserApplicationService {

    private final UserDomainService userDomainService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO registerUser(RegisterUserDTO dto) {
        User user = userMapper.toRegisterEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Set.of(Role.ROLE_USER));
        User savedUser = userDomainService.register(user);

        return userMapper.toDTO(savedUser);
    }
}
