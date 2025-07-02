package org.generalstore.modules.product.service.application;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.product.dto.AddProductDTO;
import org.generalstore.modules.product.dto.ProductDTO;
import org.generalstore.modules.product.entity.Product;
import org.generalstore.modules.product.mapper.ProductMapper;
import org.generalstore.modules.product.service.domain.ProductDomainService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ProductApplicationServiceImpl implements ProductApplicationService {

    private final ProductDomainService productDomainService;
    private final ProductMapper productMapper;

    @Override
    public ProductDTO addProduct(AddProductDTO sourceDTO) {
        Product product = productMapper.toAddEntity(sourceDTO);
        return productMapper.toDTO(productDomainService.addProduct(product));
    }
}