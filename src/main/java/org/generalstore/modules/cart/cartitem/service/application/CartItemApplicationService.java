package org.generalstore.modules.cart.cartitem.service.application;

import org.generalstore.modules.cart.cartitem.entity.CartItem;
import org.generalstore.modules.cart.core.entity.Cart;

public interface CartItemApplicationService {
    CartItem assignItemToCart(CartItem item, Cart cart);
}
