package org.generalstore.modules.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.generalstore.modules.user.dto.RegisterUserDTO;
import org.generalstore.modules.user.dto.UserDTO;
import org.generalstore.modules.user.service.application.UserApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserApplicationService userApplicationService;

    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO dto) {
        UserDTO saved = userApplicationService.registerUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
