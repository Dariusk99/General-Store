package org.generalstore.modules.cart.controller;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.cart.service.application.CartApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartApi {

    private final CartApplicationService cartApplicationService;

    @GetMapping
    public ResponseEntity<?> getMyCart() {
        return null;
    }
}
