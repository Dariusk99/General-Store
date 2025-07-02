package org.generalstore.modules.auth.controller;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.auth.service.AuthService;
import org.generalstore.modules.user.dto.UserLoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO dto) {
        String jwtToken = authService.login(dto);
        return ResponseEntity.ok(Collections.singletonMap("jwtToken", jwtToken));
    }
}