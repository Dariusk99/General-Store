package org.generalstore.modules.product.adapter;

import org.generalstore.modules.product.entity.Product;

public interface ProductPort {

    Product getProductById(Long id);
}
