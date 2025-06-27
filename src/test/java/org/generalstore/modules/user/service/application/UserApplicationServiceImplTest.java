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
    void shouldRegisterUserSucessfully() {
        // Given
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setEmail("qwe@email.com");
        registerUserDTO.setUsername("username");
        registerUserDTO.setPassword("password");

        User userEntity = new User();
        userEntity.setEmail(registerUserDTO.getEmail());
        userEntity.setUsername(registerUserDTO.getUsername());

        User savedUser = new User(
                1L,
                registerUserDTO.getEmail(),
                registerUserDTO.getUsername(),
                "encodedpassword",
                true,
                Set.of(Role.ROLE_USER)
        );

        UserDTO expectedDTO = new UserDTO(
                1L,
                registerUserDTO.getUsername()
        );

        // When
        when(userMapper.toRegisterEntity(registerUserDTO)).thenReturn(userEntity);
        when(passwordEncoder.encode(registerUserDTO.getPassword())).thenReturn("encodedpassword");
        when(userDomainService.register(userEntity)).thenReturn(savedUser);
        when(userMapper.toDTO(savedUser)).thenReturn(expectedDTO);

        // Execute
        UserDTO result = userApplicationService.registerUser(registerUserDTO);

        // Then
        assertThat(result).isEqualTo(expectedDTO);
        assertThat(userEntity.getPassword()).isEqualTo("encodedpassword");
        assertThat(userEntity.getRoles()).containsExactly(Role.ROLE_USER);

        verify(userMapper).toRegisterEntity(registerUserDTO);
        verify(passwordEncoder).encode(registerUserDTO.getPassword());
        verify(userDomainService).register(userEntity);
        verify(userMapper).toDTO(savedUser);
    }
}
