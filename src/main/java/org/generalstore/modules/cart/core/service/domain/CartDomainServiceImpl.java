package org.generalstore.modules.cart.core.service.domain;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.cart.core.entity.Cart;
import org.generalstore.modules.cart.core.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CartDomainServiceImpl implements CartDomainService{

    private final CartRepository cartRepository;

    @Override
    public Cart getCartByUserId(Long id) {
        return cartRepository.findByUserId(id);
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
