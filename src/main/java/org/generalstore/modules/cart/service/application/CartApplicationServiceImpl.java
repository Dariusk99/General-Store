package org.generalstore.modules.cart.service.application;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.cart.dto.CartDTO;
import org.generalstore.modules.cart.service.domain.CartDomainService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CartApplicationServiceImpl implements CartApplicationService{

    private final CartDomainService cartDomainService;

    @Override
    public CartDTO getUserCartOrCreate() {
        return null;
    }
}
