package org.generalstore.modules.cart.repository;

import org.generalstore.modules.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Cart findByUserId(Long id);
}
