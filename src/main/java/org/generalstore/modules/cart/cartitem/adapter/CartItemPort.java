package org.generalstore.modules.cart.cartitem.adapter;

import org.generalstore.modules.cart.cartitem.entity.CartItem;
import org.generalstore.modules.cart.core.entity.Cart;

public interface CartItemPort {
    CartItem assignItemToCart(CartItem item, Cart cart);
}
