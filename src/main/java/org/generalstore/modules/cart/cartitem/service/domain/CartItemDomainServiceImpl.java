package org.generalstore.modules.cart.cartitem.service.domain;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.cart.cartitem.entity.CartItem;
import org.generalstore.modules.cart.cartitem.repository.CartItemRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CartItemDomainServiceImpl implements CartItemDomainService {

    private final CartItemRepository cartItemRepository;

    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }
}
