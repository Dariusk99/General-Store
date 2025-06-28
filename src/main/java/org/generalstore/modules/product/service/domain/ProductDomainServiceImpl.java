package org.generalstore.modules.product.service.domain;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.product.entity.Product;
import org.generalstore.modules.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class ProductDomainServiceImpl implements ProductDomainService {

    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product sourceProduct) {

        Optional<Product> existProduct = productRepository.findByName(sourceProduct.getName());

        if (existProduct.isEmpty()) {
            return productRepository.save(sourceProduct);
        }

        Product productFromDb = existProduct.get();

        int summedQuantity = productFromDb.getQuantity() + sourceProduct.getQuantity();
        productFromDb.setQuantity(summedQuantity);

        return productRepository.save(productFromDb);
    }
}
