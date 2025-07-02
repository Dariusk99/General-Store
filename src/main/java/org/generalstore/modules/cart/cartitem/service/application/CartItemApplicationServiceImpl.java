package org.generalstore.modules.cart.cartitem.service.application;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.cart.cartitem.entity.CartItem;
import org.generalstore.modules.cart.cartitem.service.domain.CartItemDomainService;
import org.generalstore.modules.cart.core.entity.Cart;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CartItemApplicationServiceImpl implements CartItemApplicationService {
    
    private final CartItemDomainService cartItemDomainService;

    @Override
    public CartItem assignItemToCart(CartItem item, Cart cart) {
        item.setCart(cart);
        return cartItemDomainService.saveCartItem(item);
    }
}
