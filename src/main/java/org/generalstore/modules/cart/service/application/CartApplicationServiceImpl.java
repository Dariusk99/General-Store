package org.generalstore.modules.cart.service.application;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.cart.dto.CartDTO;
import org.generalstore.modules.cart.entity.Cart;
import org.generalstore.modules.cart.factory.CartFactory;
import org.generalstore.modules.cart.mapper.CartMapper;
import org.generalstore.modules.cart.service.domain.CartDomainService;
import org.generalstore.modules.user.provider.UserPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CartApplicationServiceImpl implements CartApplicationService {

    private final CartDomainService cartDomainService;
    private final UserPort userPort;
    private final CartMapper cartMapper;
    private final CartFactory cartFactory;

    @Override
    public CartDTO getCartOrCreate(String username, CartDTO localStorageCart) {
        if (username != null) {
            Cart userCart = cartDomainService.getCartByUserId(userPort.getUserId(username));

            if (localStorageCart != null) {
                Cart lsCart = cartMapper.toEntity(localStorageCart);
                userCart.mergeCart(lsCart);
            }
            return cartMapper.toDTO(userCart);
        } else {
            if (localStorageCart != null) {
                return localStorageCart;
            } else {
                return cartMapper.toDTO(cartFactory.createCartWithNoUser());
            }
        }
    }
}