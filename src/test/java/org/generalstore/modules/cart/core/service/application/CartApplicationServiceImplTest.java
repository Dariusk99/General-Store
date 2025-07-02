package org.generalstore.modules.cart.core.service.application;

import org.generalstore.modules.cart.core.dto.CartDTO;
import org.generalstore.modules.cart.core.entity.Cart;
import org.generalstore.modules.cart.core.mapper.CartMapper;
import org.generalstore.modules.cart.core.service.domain.CartDomainService;
import org.generalstore.modules.user.adapter.UserPort;
import org.generalstore.modules.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.UUID;

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
    void getCartOrCreate_shouldReturnUserCartByUserId_whenUserExists() {
        // Arrange
        String sourceUsername = "marek";
        Long userId = 1L;
        CartDTO localStorageCartDTO = new CartDTO(
                UUID.randomUUID(),
                new ArrayList<>()
        );

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
                userFromCart,
                new ArrayList<>()
        );

        CartDTO expectedResult = new CartDTO(
                null,
                new ArrayList<>()
        );

        when(cartDomainService.getCartByUserId(userId)).thenReturn(cartFromDb);
        when(userPort.getUserId(sourceUsername)).thenReturn(userId);
        when(cartMapper.toDTO(cartFromDb)).thenReturn(expectedResult);

        // Act
        CartDTO actualResult = cartApplicationService.getUserCartOrCreate(sourceUsername, localStorageCartDTO);

        // Assert
        assertThat(actualResult)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedResult);
        verify(cartDomainService).getCartByUserId(userId);
        verify(userPort).getUserId(sourceUsername);
        verify(cartMapper).toDTO(cartFromDb);
    }

    @Test
    void addProductToCart_returnCartDTO_whenProductAddSuccessful() {
        Long productId = 1L;
        String sourceUsername = "marek";
    }
}
