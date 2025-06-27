package org.generalstore.modules.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {

    @NotBlank(message = "Username cannot be blank.")
    @Size(min = 4, max = 15, message = "Username must be between 3 and 15 characters.")
    private String username;

    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Invalid email address.")
    private String email;

    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 6, max = 25, message = "Password must be between 6 and 25 characters.")
    private String password;
}
