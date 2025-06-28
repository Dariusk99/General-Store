package org.generalstore.modules.user.service.application;

import org.generalstore.modules.user.dto.RegisterUserDTO;
import org.generalstore.modules.user.dto.UserDTO;
import org.generalstore.modules.user.entity.Role;
import org.generalstore.modules.user.entity.User;
import org.generalstore.modules.user.mapper.UserMapper;
import org.generalstore.modules.user.service.domain.UserDomainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserApplicationServiceImplTest {

    @Mock
    private UserDomainService userDomainService;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    UserApplicationServiceImpl userApplicationService;

    @Test
    void registerUser_shouldReturnUserDTO_whenRegisterSuccessful() {
        // Arrange
        String encodedPassword = "encodedpassword";

        RegisterUserDTO sourceUserDTO = new RegisterUserDTO(
                "email@email.com",
                "username",
                "password"
        );

        User userToSave = new User(
                null,
                sourceUserDTO.getEmail(),
                sourceUserDTO.getUsername(),
                encodedPassword,
                true,
                Set.of(Role.ROLE_USER)
        );

        User savedUser = new User(
                1L,
                sourceUserDTO.getEmail(),
                sourceUserDTO.getUsername(),
                encodedPassword,
                userToSave.isActive(),
                userToSave.getRoles()
        );

        UserDTO expectedResult = new UserDTO(
                savedUser.getId(),
                sourceUserDTO.getUsername()
        );

        when(userMapper.toRegisterEntity(sourceUserDTO)).thenReturn(userToSave);
        when(passwordEncoder.encode(sourceUserDTO.getPassword())).thenReturn(encodedPassword);
        when(userDomainService.register(userToSave)).thenReturn(savedUser);
        when(userMapper.toDTO(savedUser)).thenReturn(expectedResult);

        // Act
        UserDTO actualResult = userApplicationService.registerUser(sourceUserDTO);

        // Assert
        assertThat(actualResult).isEqualTo(expectedResult);
        assertThat(userToSave.getPassword()).isEqualTo(encodedPassword);
        assertThat(userToSave.getRoles()).containsExactly(Role.ROLE_USER);

        verify(userMapper).toRegisterEntity(sourceUserDTO);
        verify(passwordEncoder).encode(sourceUserDTO.getPassword());
        verify(userDomainService).register(userToSave);
        verify(userMapper).toDTO(savedUser);
    }
}
