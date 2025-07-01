package org.generalstore.modules.cart.core.controller;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.cart.core.dto.CartDTO;
import org.generalstore.modules.cart.core.service.application.CartApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartApi {

    private final CartApplicationService cartApplicationService;

    @PostMapping
    public ResponseEntity<?> addProductToCart(@AuthenticationPrincipal UserDetails user, @RequestBody(required = false) CartDTO localStorageCart) {
        String username = user != null ? user.getUsername() : null;
        CartDTO cartDTO = cartApplicationService.getCartOrCreate(username, localStorageCart);
        return ResponseEntity.ok(cartDTO);
    }
}