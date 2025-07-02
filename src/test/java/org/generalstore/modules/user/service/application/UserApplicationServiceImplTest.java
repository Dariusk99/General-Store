package org.generalstore.modules.user.service.application;

import org.generalstore.modules.cart.core.adapter.CartPort;
import org.generalstore.modules.cart.core.entity.Cart;
import org.generalstore.modules.user.dto.RegisterUserDTO;
import org.generalstore.modules.user.dto.UserDTO;
import org.generalstore.modules.user.entity.Role;
import org.generalstore.modules.user.entity.User;
import org.generalstore.modules.user.factory.UserFactory;
import org.generalstore.modules.user.mapper.UserMapper;
import org.generalstore.modules.user.service.domain.UserDomainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserApplicationServiceImplTest {

    @Mock
    private UserDomainService userDomainService;
    @Mock
    private UserMapper userMapper;
    @Mock
    private CartPort cartPort;
    @Mock
    private UserFactory userFactory;

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

        when(userFactory.createUserFromDTO(sourceUserDTO)).thenReturn(userToSave);
        when(userDomainService.register(userToSave)).thenReturn(savedUser);
        when(cartPort.saveCartForUser(any(Cart.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(userMapper.toDTO(savedUser)).thenReturn(expectedResult);

        // Act
        UserDTO actualResult = userApplicationService.registerUserWithCart(sourceUserDTO);

        // Assert
        assertThat(actualResult).isEqualTo(expectedResult);
        assertThat(userToSave.getPassword()).isEqualTo(encodedPassword);
        assertThat(userToSave.getRoles()).containsExactly(Role.ROLE_USER);

        verify(userFactory).createUserFromDTO(sourceUserDTO);
        verify(userDomainService).register(userToSave);
        verify(cartPort).saveCartForUser(any(Cart.class));
        verify(userMapper).toDTO(savedUser);
    }
}
