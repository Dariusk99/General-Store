package org.generalstore.modules.cart.cartitem.adapter;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.cart.cartitem.entity.CartItem;
import org.generalstore.modules.cart.cartitem.service.application.CartItemApplicationService;
import org.generalstore.modules.cart.core.entity.Cart;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CartItemAdapter implements CartItemPort {

    private final CartItemApplicationService cartItemApplicationService;

    @Override
    public CartItem assignItemToCart(CartItem item, Cart cart) {
        return cartItemApplicationService.assignItemToCart(item, cart);
    }
}
