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
    void register_shouldRegisterUser_WhenEmailNotExists() {
        // Arrange
        User source = new User(
                1L,
                "email@email.com",
                "username",
                "password",
                true,
                Set.of(Role.ROLE_USER)
        );
        when(repository.existsByEmail(source.getEmail())).thenReturn(false);
        when(repository.save(source)).thenReturn(source);

        // Act
        User saved = userService.register(source);

        // Assert
        assertThat(saved).isEqualTo(source);
        verify(repository).existsByEmail(source.getEmail());
        verify(repository).save(source);
    }

    @Test
    void register_shouldThrowException_whenUserWithThisEmailExists() {
        // Arrange
        User source = new User(
                1L,
                "email@email.com",
                "username",
                "password",
                true,
                Set.of(Role.ROLE_USER)
        );
        when(repository.existsByEmail(source.getEmail())).thenReturn(true);

        // Act
        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> {
            userService.register(source);
        });

        // Assert
        assertEquals("User already exist with email: " + source.getEmail(), exception.getMessage());
        verify(repository, never()).save(source);
    }
}
