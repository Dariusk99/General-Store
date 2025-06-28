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
        RegisterUserDTO source = new RegisterUserDTO();
        source.setEmail("qwe@email.com");
        source.setUsername("username");
        source.setPassword("password");

        User sourceEntity = new User();
        sourceEntity.setEmail(source.getEmail());
        sourceEntity.setUsername(source.getUsername());

        User savedUser = new User(
                1L,
                source.getEmail(),
                source.getUsername(),
                "encodedpassword",
                true,
                Set.of(Role.ROLE_USER)
        );

        UserDTO expectedDTO = new UserDTO(
                1L,
                source.getUsername()
        );

        when(userMapper.toRegisterEntity(source)).thenReturn(sourceEntity);
        when(passwordEncoder.encode(source.getPassword())).thenReturn("encodedpassword");
        when(userDomainService.register(sourceEntity)).thenReturn(savedUser);
        when(userMapper.toDTO(savedUser)).thenReturn(expectedDTO);

        // Act
        UserDTO result = userApplicationService.registerUser(source);

        // Assert
        assertThat(result).isEqualTo(expectedDTO);
        assertThat(sourceEntity.getPassword()).isEqualTo("encodedpassword");
        assertThat(sourceEntity.getRoles()).containsExactly(Role.ROLE_USER);

        verify(userMapper).toRegisterEntity(source);
        verify(passwordEncoder).encode(source.getPassword());
        verify(userDomainService).register(sourceEntity);
        verify(userMapper).toDTO(savedUser);
    }
}
