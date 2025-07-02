package org.generalstore.modules.cart.core.service.application;

import org.generalstore.modules.cart.core.dto.CartDTO;

public interface CartApplicationService {

    CartDTO getUserCartOrCreate(String username, CartDTO localStorageCart);

    CartDTO addProductToCart(Long productId, String username, CartDTO localStorageCart);
}
