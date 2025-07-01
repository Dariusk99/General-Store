package org.generalstore.modules.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.generalstore.modules.auth.util.JwtUtil;
import org.generalstore.modules.user.dto.RegisterUserDTO;
import org.generalstore.modules.user.dto.UserDTO;
import org.generalstore.modules.user.service.application.UserApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserApi.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserApplicationService userApplicationService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Test
    void registerUser_shouldReturn201AndUserDTO_whenRequestIsValid () throws Exception {
        // Arrange
        RegisterUserDTO sourceUserDTO = new RegisterUserDTO(
                "username",
                "email@email.com",
                "password"
        );

        UserDTO expectedResponse = new UserDTO(
                null,
                sourceUserDTO.getUsername()
        );

        when(userApplicationService.registerUser(any(RegisterUserDTO.class)))
                .thenReturn(expectedResponse);

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
                .isEqualTo(expectedResponse);
    }
}
