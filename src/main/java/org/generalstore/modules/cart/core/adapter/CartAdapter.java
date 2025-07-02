package org.generalstore.modules.cart.core.adapter;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.cart.core.entity.Cart;
import org.generalstore.modules.cart.core.service.domain.CartDomainService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CartAdapter implements CartPort {

    private final CartDomainService cartDomainService;

    @Override
    public Cart saveCartForUser(Cart cart) {
        return cartDomainService.createCart(cart);
    }
}
