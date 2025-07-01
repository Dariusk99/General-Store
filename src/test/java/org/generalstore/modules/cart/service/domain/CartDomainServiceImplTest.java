package org.generalstore.modules.cart.service.domain;

import org.generalstore.modules.cart.core.entity.Cart;
import org.generalstore.modules.cart.core.repository.CartRepository;
import org.generalstore.modules.cart.core.service.domain.CartDomainServiceImpl;
import org.generalstore.modules.user.entity.Role;
import org.generalstore.modules.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartDomainServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartDomainServiceImpl cartDomainServiceImpl;

    @Test
    void getCartByUserId_shouldGetCart_whenUserExists() {
        // Arrange
        Long sourceUserId = 1L;

        User expectedUser = new User(
                sourceUserId,
                "email@email.com",
                "username",
                "password",
                true,
                Set.of(Role.ROLE_USER)
        );

        Cart expectedResult = new Cart(
                null,
                expectedUser
        );

        when(cartRepository.findByUserId(sourceUserId)).thenReturn(expectedResult);

        // Act
        Cart actualResult = cartDomainServiceImpl.getCartByUserId(sourceUserId);

        // Assert
        assertThat(actualResult)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedResult);
        verify(cartRepository).findByUserId(sourceUserId);
    }
}
