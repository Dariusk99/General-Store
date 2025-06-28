package org.generalstore.modules.user.service.domain;

import jakarta.persistence.EntityExistsException;
import org.generalstore.modules.user.entity.Role;
import org.generalstore.modules.user.entity.User;
import org.generalstore.modules.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDomainServiceImplTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserDomainServiceImpl userService;

    @Test
    void register_shouldRegisterUser_whenEmailNotExists() {
        // Arrange
        User sourceUser = new User(
                null,
                "email@email.com",
                "username",
                "password",
                true,
                Set.of(Role.ROLE_USER)
        );

        when(repository.existsByEmail(sourceUser.getEmail())).thenReturn(false);
        when(repository.save(sourceUser)).thenReturn(sourceUser);

        // Act
        User actualResult = userService.register(sourceUser);

        // Assert
        assertThat(actualResult).isEqualTo(sourceUser);
        verify(repository).existsByEmail(sourceUser.getEmail());
        verify(repository).save(sourceUser);
    }

    @Test
    void register_shouldThrowException_whenUserWithThisEmailExists() {
        // Arrange
        User sourceUser = new User(
                null,
                "email@email.com",
                "username",
                "password",
                true,
                Set.of(Role.ROLE_USER)
        );

        when(repository.existsByEmail(sourceUser.getEmail())).thenReturn(true);

        // Act
        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> {
            userService.register(sourceUser);
        });

        // Assert
        assertEquals("User already exist with email: " + sourceUser.getEmail(), exception.getMessage());
        verify(repository, never()).save(sourceUser);
    }
}
