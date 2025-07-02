package org.generalstore.modules.cart.core.controller;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.cart.core.dto.CartDTO;
import org.generalstore.modules.cart.core.service.application.CartApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartApi {

    private final CartApplicationService cartApplicationService;

    @Transactional
    @PostMapping
    public ResponseEntity<?> addProductToCart(@AuthenticationPrincipal UserDetails user,
                                              @RequestBody(required = false) CartDTO localStorageCart,
                                              @RequestParam(name = "productId") Long productId) {
        String username = user != null ? user.getUsername() : null;
        CartDTO cartDTO = cartApplicationService.addProductToCart(productId, username, localStorageCart);
        return ResponseEntity.ok(cartDTO);
    }
}