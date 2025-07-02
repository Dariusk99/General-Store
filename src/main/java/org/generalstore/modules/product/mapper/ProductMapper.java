package org.generalstore.modules.product.mapper;

import org.generalstore.modules.product.dto.AddProductDTO;
import org.generalstore.modules.product.dto.ProductDTO;
import org.generalstore.modules.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

    ProductDTO toDTO(Product product);

    Product toEntity(ProductDTO productDTO);

    @Mapping(target = "id", ignore = true)
    Product toAddEntity(AddProductDTO addProductDTO);
}
