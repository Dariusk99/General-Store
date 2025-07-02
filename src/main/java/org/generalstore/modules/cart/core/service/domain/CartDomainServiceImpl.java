package org.generalstore.modules.cart.core.service.domain;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.cart.core.entity.Cart;
import org.generalstore.modules.cart.core.repository.CartRepository;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Override
    public Cart updateCart(Cart cart) {
        try {
            return cartRepository.save(cart);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Problem with updating cart.");
        }
    }
}
