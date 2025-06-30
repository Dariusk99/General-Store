package org.generalstore.modules.cart.service.domain;

import org.generalstore.modules.cart.entity.Cart;

public interface CartDomainService {
    Cart getCartByUserId(Long id);
}
