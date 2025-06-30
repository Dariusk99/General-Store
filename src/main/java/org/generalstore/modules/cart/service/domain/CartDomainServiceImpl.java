package org.generalstore.modules.cart.service.domain;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.cart.entity.Cart;
import org.generalstore.modules.cart.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CartDomainServiceImpl implements CartDomainService{

    private final CartRepository cartRepository;

    @Override
    public Cart getCartByUserId(Long id) {
        return cartRepository.findByUserId(id);
    }
}
