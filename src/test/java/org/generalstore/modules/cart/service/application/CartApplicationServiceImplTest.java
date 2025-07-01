package org.generalstore.modules.cart.service.application;

import org.generalstore.modules.cart.dto.CartDTO;
import org.generalstore.modules.cart.entity.Cart;
import org.generalstore.modules.cart.mapper.CartMapper;
import org.generalstore.modules.cart.service.domain.CartDomainService;
import org.generalstore.modules.user.entity.User;
import org.generalstore.modules.user.provider.UserPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartApplicationServiceImplTest {

    @Mock
    private CartDomainService cartDomainService;

    @Mock
    private UserPort userPort;

    @Mock
    private CartMapper cartMapper;

    @InjectMocks
    private CartApplicationServiceImpl cartApplicationService;

    @Test
    void getCartOrCreate_shouldReturnCartByUserId_whenUserExists() {
        // Arrange
        String sourceUsername = "marek";
        Long userId = 1L;

        User userFromCart = new User(
                userId,
                null,
                null,
                null,
                true,
                null
        );

        Cart cartFromDb = new Cart(
                null,
                userFromCart
        );

        CartDTO expectedResult = new CartDTO(
                null
        );

        when(cartDomainService.getCartByUserId(userId)).thenReturn(cartFromDb);
        when(userPort.getUserId(sourceUsername)).thenReturn(userId);
        when(cartMapper.toDTO(cartFromDb)).thenReturn(expectedResult);

        // Act
        CartDTO actualResult = cartApplicationService.getCartOrCreate(sourceUsername);

        // Assert
        assertThat(actualResult)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedResult);
        verify(cartDomainService).getCartByUserId(userId);
        verify(userPort).getUserId(sourceUsername);
        verify(cartMapper).toDTO(cartFromDb);
    }
}
