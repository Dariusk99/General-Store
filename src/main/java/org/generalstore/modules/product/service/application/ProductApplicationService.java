package org.generalstore.modules.product.service.application;

import org.generalstore.modules.product.dto.AddProductDTO;
import org.generalstore.modules.product.dto.ProductDTO;

public interface ProductApplicationService {
     ProductDTO addProduct(AddProductDTO sourceDTO);
}
