package org.generalstore.modules.product.controller;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.product.dto.AddProductDTO;
import org.generalstore.modules.product.dto.ProductDTO;
import org.generalstore.modules.product.service.application.ProductApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductApi {

    private final ProductApplicationService productApplicationService;

    @PostMapping
    public ResponseEntity<?> addProduct(AddProductDTO requestDTO) {
        ProductDTO saved = productApplicationService.addProduct(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
