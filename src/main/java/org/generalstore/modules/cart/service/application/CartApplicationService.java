package org.generalstore.modules.cart.service.application;

import org.generalstore.modules.cart.dto.CartDTO;

public interface CartApplicationService {

    CartDTO getUserCartOrCreate();
}
