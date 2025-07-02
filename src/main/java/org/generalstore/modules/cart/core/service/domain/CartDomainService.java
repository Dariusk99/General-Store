package org.generalstore.modules.cart.core.service.domain;

import org.generalstore.modules.cart.core.entity.Cart;

public interface CartDomainService {
    Cart getCartByUserId(Long id);
    Cart createCart(Cart cart);

    Cart updateCart(Cart cart);
}
