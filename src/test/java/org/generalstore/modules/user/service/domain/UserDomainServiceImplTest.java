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
    void shouldRegisterUserWhenEmailNotExists() {
        // Given
        User user = new User(
                1L,
                "mirek@email.com",
                "mirek",
                "pawel123",
                true,
                Set.of(Role.ROLE_USER)
        );

        // When
        when(repository.existsByEmail(user.getEmail())).thenReturn(false);
        when(repository.save(user)).thenReturn(user);

        // Execute
        User saved = userService.register(user);

        // Then
        assertThat(saved).isEqualTo(user);
        verify(repository).existsByEmail(user.getEmail());
        verify(repository).save(user);
    }

    @Test
    void shouldThrowExceptionWhenUserWithThisEmailExists() {
        User user = new User(
                1L,
                "mirek@email.com",
                "mirek",
                "pawel123",
                true,
                Set.of(Role.ROLE_USER)
        );

        when(repository.existsByEmail(user.getEmail())).thenReturn(true);

        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> {
            userService.register(user);
        });

        assertEquals("User already exist with email: " + user.getEmail(), exception.getMessage());
        verify(repository, never()).save(user);
    }
}
