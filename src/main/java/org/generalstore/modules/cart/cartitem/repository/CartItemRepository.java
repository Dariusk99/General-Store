package org.generalstore.modules.cart.cartitem.repository;

import org.generalstore.modules.cart.cartitem.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
