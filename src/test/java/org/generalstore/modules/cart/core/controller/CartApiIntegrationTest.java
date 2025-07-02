//package org.generalstore.modules.cart.core.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.generalstore.modules.auth.util.JwtUtil;
//import org.generalstore.modules.cart.core.dto.CartDTO;
//import org.generalstore.modules.user.dto.RegisterUserDTO;
//import org.generalstore.modules.user.service.application.UserApplicationService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.UUID;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@Transactional
//@ActiveProfiles("test")
//public class CartApiIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private UserApplicationService userApplicationService;
//
//    @BeforeEach
//    void setup() {
//        RegisterUserDTO registerUserDTO = new RegisterUserDTO(
//                "user@email.com",
//                "username",
//                "password"
//        );
//
//        userApplicationService.registerUserWithCart(registerUserDTO);
//    }
//
//    @Test
//    void addProductToCart_returnNewLocalStorageCart_whenNotAuthenticated() throws Exception {
//        // Arrange
//        CartDTO localStorageCartDTO = new CartDTO(
//                UUID.randomUUID(),
//                new ArrayList<>()
//        );
//
//        // Act && Assert
//        MvcResult mvcResult = mockMvc.perform(post("/api/carts")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(localStorageCartDTO)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//
//        String jsonResponse = mvcResult.getResponse().getContentAsString();
//        CartDTO actualResponse = objectMapper.readValue(jsonResponse, CartDTO.class);
//
//        assertThat(actualResponse).isNotNull();
//    }
//
//    @Test
//    void addProductToCart_returnUserCart_whenUserAuthenticated() throws Exception {
//        // Arrange
//        String token = jwtUtil.generateToken("username");
//        CartDTO localStorageCartDTO = new CartDTO(UUID.randomUUID(), new ArrayList<>());
//
//        // Act && Assert
//        mockMvc.perform(post("/api/carts")
//                        .header("Authorization", "Bearer " + token)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(localStorageCartDTO)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(result -> {
//                    String json = result.getResponse().getContentAsString();
//                    CartDTO actualCart = objectMapper.readValue(json, CartDTO.class);
//                    assertThat(actualCart).isNotNull();
//                });
//    }
//}
