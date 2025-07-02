package org.generalstore.modules.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.generalstore.modules.user.dto.RegisterUserDTO;
import org.generalstore.modules.user.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class UserApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerUser_shouldReturn201AndUserDTO_whenRequestIsValid() throws Exception {
        // Arrange
        RegisterUserDTO sourceUserDTO = new RegisterUserDTO(
                "username",
                "email@email.com",
                "password"
        );

        UserDTO expectedResultDTO = new UserDTO(
                null,
                sourceUserDTO.getUsername()
        );

        // Act & Assert
        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sourceUserDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        UserDTO actualResponse = objectMapper.readValue(jsonResponse, UserDTO.class);

        assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedResultDTO);
    }
}
