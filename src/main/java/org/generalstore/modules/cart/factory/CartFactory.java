package org.generalstore.modules.cart.factory;

import org.generalstore.modules.cart.entity.Cart;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CartFactory {

    public Cart createCartWithNoUser() {
        return new Cart(
                UUID.randomUUID(),
                null
        );
    }
}
