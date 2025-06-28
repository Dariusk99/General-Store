package org.generalstore.modules.product.service.domain;

import org.generalstore.modules.product.entity.Product;
import org.generalstore.modules.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductDomainServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductDomainServiceImpl productDomainService;

    @Test
    void addProduct_shouldAddProductNewProduct_whenProductByNameNotExists() {
        // Arrange
        Product sourceProduct = new Product(
                null,
                "name",
                "description",
                1,
                BigDecimal.valueOf(12.99)
        );

        when(productRepository.findByName(sourceProduct.getName())).thenReturn(Optional.empty());
        when(productRepository.save(sourceProduct)).thenReturn(sourceProduct);

        // Act
        Product actualResult = productDomainService.addProduct(sourceProduct);

        // Assert
        assertThat(actualResult).isEqualTo(sourceProduct);
        verify(productRepository).findByName(sourceProduct.getName());
        verify(productRepository).save(sourceProduct);
    }

    @Test
    void addProduct_shouldSumQuantityAndAddProduct_whenProductByNameExists() {
        // Arrange
        int sourceQuantity = 5;
        int existQuantity = 2;

        Product sourceProduct = new Product(
                null,
                "name",
                "description",
                sourceQuantity,
                BigDecimal.valueOf(12.99)
        );

        Product existProduct = new Product(
                1L,
                sourceProduct.getName(),
                "description",
                existQuantity,
                BigDecimal.valueOf(12.99)
        );

        Product expectedResult = new Product(
                null,
                sourceProduct.getName(),
                sourceProduct.getDescription(),
                existQuantity + sourceQuantity,
                sourceProduct.getPrice()
        );

        when(productRepository.findByName(sourceProduct.getName())).thenReturn(Optional.of(existProduct));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Product actualResult = productDomainService.addProduct(sourceProduct);

        // Assert
        assertThat(actualResult)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedResult);
        verify(productRepository).findByName(sourceProduct.getName());
        verify(productRepository).save(existProduct);
    }
}
