package org.generalstore.modules.auth.service;

import lombok.RequiredArgsConstructor;
import org.generalstore.modules.auth.exception.InvalidLoginException;
import org.generalstore.modules.auth.util.JwtUtil;
import org.generalstore.modules.user.dto.UserLoginDTO;
import org.generalstore.modules.user.service.domain.MyUserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final MyUserDetailsServiceImpl userDetailsService;

    @Override
    public String login(UserLoginDTO dto) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new InvalidLoginException("Bad credentials.");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
        return jwtUtil.generateToken(userDetails.getUsername());
    }
}