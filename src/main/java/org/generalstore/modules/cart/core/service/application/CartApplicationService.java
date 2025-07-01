package org.generalstore.modules.cart.core.service.application;

import org.generalstore.modules.cart.core.dto.CartDTO;

public interface CartApplicationService {

    CartDTO getCartOrCreate(String username, CartDTO localStorageCart);
}
