package org.generalstore.modules.cart.core.service.application;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.cart.cartitem.adapter.CartItemPort;
import org.generalstore.modules.cart.cartitem.entity.CartItem;
import org.generalstore.modules.cart.core.dto.CartDTO;
import org.generalstore.modules.cart.core.entity.Cart;
import org.generalstore.modules.cart.core.factory.CartFactory;
import org.generalstore.modules.cart.core.mapper.CartMapper;
import org.generalstore.modules.cart.core.service.domain.CartDomainService;
import org.generalstore.modules.product.adapter.ProductPort;
import org.generalstore.modules.product.entity.Product;
import org.generalstore.modules.user.adapter.UserPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CartApplicationServiceImpl implements CartApplicationService {

    private final CartDomainService cartDomainService;
    private final UserPort userPort;
    private final CartMapper cartMapper;
    private final CartFactory cartFactory;
    private final ProductPort productPort;
    private final CartItemPort cartItemPort;

    @Override
    public CartDTO getUserCartOrCreate(String username, CartDTO localStorageCart) {
        if (username != null) {
            Cart userCart = cartDomainService.getCartByUserId(userPort.getUserId(username));

            if (localStorageCart != null) {
                Cart localCart = cartMapper.toEntity(localStorageCart);
                userCart.mergeCart(localCart);
            }
            return cartMapper.toDTO(userCart);
        } else {
            if (localStorageCart != null) {
                return localStorageCart;
            } else {
                return cartMapper.toDTO(cartFactory.createCartWithNoUser());
            }
        }
    }

    @Override
    public CartDTO addProductToCart(Long productId, String username, CartDTO localStorageCart) {
        Product product = productPort.getProductById(productId);
        Cart myCart = cartMapper.toEntity(getUserCartOrCreate(username, localStorageCart));
        CartItem cartItem = new CartItem(
                null,
                product.getName(),
                1,
                product,
                myCart
        );

        // Add item to cart and update cart in database.
        myCart.addItem(cartItem);
        if (myCart.getUser() != null) {
            Cart updatedCart = cartDomainService.updateCart(myCart);
            return cartMapper.toDTO(updatedCart);
        } else {
            return cartMapper.toDTO(myCart);
        }
    }
}