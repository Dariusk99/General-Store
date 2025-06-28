package org.generalstore.modules.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.generalstore.modules.user.dto.RegisterUserDTO;
import org.generalstore.modules.user.dto.UserDTO;
import org.generalstore.modules.user.service.application.UserApplicationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserApi.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    UserApplicationService userApplicationService;

    @TestConfiguration
    static class MockConfig {
        @Bean
        UserApplicationService userApplicationService() {
            return Mockito.mock(UserApplicationService.class);
        }
    }

    @Test
    void registerUser_shouldReturn201AndUserDTO_whenRequestIsValid () throws Exception {
        // Arrange
        RegisterUserDTO source = new RegisterUserDTO(
                "username",
                "email@email.com",
                "password"
        );

        UserDTO expectedDTO = new UserDTO(
                1L,
                source.getUsername()
        );

        when(userApplicationService.registerUser(any(RegisterUserDTO.class)))
                .thenReturn(expectedDTO);

        // Act & Assert
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(source)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(expectedDTO.getId()))
                .andExpect(jsonPath("$.username").value(expectedDTO.getUsername()));
    }
}
