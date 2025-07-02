package org.generalstore.modules.product.service.domain;

import org.generalstore.modules.product.entity.Product;

public interface ProductDomainService {
    Product addProduct(Product sourceProduct);
    Product getById(Long id);
}
