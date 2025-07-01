package org.generalstore.modules.cart.core.adapter;

import org.generalstore.modules.cart.core.entity.Cart;

public interface CartPort {

    Cart saveCartForUser(Cart cart);
}
