package org.generalstore.modules.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.generalstore.modules.product.dto.AddProductDTO;
import org.generalstore.modules.product.dto.ProductDTO;
import org.generalstore.modules.product.service.application.ProductApplicationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductApi.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductApiTest {

    @Autowired
    private ProductApplicationService productApplicationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        ProductApplicationService productApplicationService() {
            return Mockito.mock(ProductApplicationService.class);
        }
    }

    @Test
    void addProduct_shouldReturn201AndProductDTO_whenRequestIsValid() throws Exception {
        // Arrange
        AddProductDTO sourceProductDTO = new AddProductDTO(
                "name",
                "description",
                5,
                BigDecimal.valueOf(12.99)
        );

        ProductDTO expectedResultDTO = new ProductDTO(
                1L,
                sourceProductDTO.getName(),
                sourceProductDTO.getDescription(),
                sourceProductDTO.getQuantity(),
                sourceProductDTO.getPrice()
        );

        when(productApplicationService.addProduct(any(AddProductDTO.class)))
                .thenReturn(expectedResultDTO);

        // Act & Assert
        MvcResult mvcResult = mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sourceProductDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        ProductDTO actualResult = objectMapper.readValue(jsonResponse, ProductDTO.class);

        assertThat(actualResult)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedResultDTO);
    }
}
