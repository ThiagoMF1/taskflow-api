package com.thiagomf.taskflowapi.controller;

import com.thiagomf.taskflowapi.dto.AuthResponse;
import com.thiagomf.taskflowapi.dto.LoginRequest;
import com.thiagomf.taskflowapi.dto.RegisterRequest;
import com.thiagomf.taskflowapi.dto.UserResponse;
import com.thiagomf.taskflowapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public UserResponse getCurrentUser(Authentication authentication) {
        return authService.getCurrentUser(authentication.getName());
    }
}