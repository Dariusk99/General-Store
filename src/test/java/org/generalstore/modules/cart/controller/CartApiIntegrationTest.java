package org.generalstore.modules.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.generalstore.modules.cart.dto.CartDTO;
import org.generalstore.modules.cart.service.application.CartApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class CartApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CartApplicationService cartApplicationService;

    @Test
    void getMyCart_shouldReturn200WithUserCartDTO() throws Exception {
        // Arrange
        String sourceUsername = "testuser";
        CartDTO expectedCartDTO = new CartDTO(
                null
        );

        // Act && Assert
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/carts")
                .with(user(sourceUsername)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        CartDTO actualResponse = objectMapper.readValue(jsonResponse, CartDTO.class);

        assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedCartDTO);
    }
}
