package org.generalstore.modules.product.service.application;

import org.generalstore.modules.product.dto.AddProductDTO;
import org.generalstore.modules.product.dto.ProductDTO;
import org.generalstore.modules.product.entity.Product;
import org.generalstore.modules.product.mapper.ProductMapper;
import org.generalstore.modules.product.service.domain.ProductDomainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductApplicationServiceImplTest {

    @Mock
    private ProductDomainService productDomainService;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductApplicationServiceImpl productApplicationService;

    @Test
    void addProduct_shouldReturnMappedProductDTO_whenAddProductIsSuccessful() {
        // Arrange
        AddProductDTO sourceProductDTO = new AddProductDTO(
                "name",
                "description",
                1,
                BigDecimal.valueOf(12.99)
        );

        Product mappedProduct = new Product(
                null,
                sourceProductDTO.getName(),
                sourceProductDTO.getDescription(),
                sourceProductDTO.getQuantity(),
                sourceProductDTO.getPrice()
        );

        Product savedProduct = new Product(
                1L,
                sourceProductDTO.getName(),
                sourceProductDTO.getDescription(),
                sourceProductDTO.getQuantity(),
                sourceProductDTO.getPrice()
        );

        ProductDTO expectedResult = new ProductDTO(
                savedProduct.getId(),
                sourceProductDTO.getName(),
                sourceProductDTO.getDescription(),
                sourceProductDTO.getQuantity(),
                sourceProductDTO.getPrice()
        );

        when(productMapper.toAddEntity(sourceProductDTO)).thenReturn(mappedProduct);
        when(productDomainService.addProduct(mappedProduct)).thenReturn(savedProduct);
        when(productMapper.toDTO(savedProduct)).thenReturn(expectedResult);

        // Act
        ProductDTO actualResult = productApplicationService.addProduct(sourceProductDTO);

        // Assert
        assertThat(actualResult).isEqualTo(expectedResult);
        verify(productMapper).toAddEntity(sourceProductDTO);
        verify(productDomainService).addProduct(mappedProduct);
        verify(productMapper).toDTO(savedProduct);
    }
}
