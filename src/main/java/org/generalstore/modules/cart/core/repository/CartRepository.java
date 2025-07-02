package org.generalstore.modules.cart.core.repository;

import org.generalstore.modules.cart.core.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Cart findByUserId(Long id);
}
