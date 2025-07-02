package org.generalstore.modules.product.adapter;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.product.entity.Product;
import org.generalstore.modules.product.service.domain.ProductDomainService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ProductAdapter implements ProductPort {

    private final ProductDomainService productDomainService;

    @Override
    public Product getProductById(Long id) {
        return productDomainService.getById(id);
    }
}
