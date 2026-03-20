package com.thiagomf.taskflowapi.service;

import com.thiagomf.taskflowapi.dto.AuthResponse;
import com.thiagomf.taskflowapi.dto.LoginRequest;
import com.thiagomf.taskflowapi.dto.RegisterRequest;
import com.thiagomf.taskflowapi.entity.Role;
import com.thiagomf.taskflowapi.entity.User;
import com.thiagomf.taskflowapi.exception.DuplicateResourceException;
import com.thiagomf.taskflowapi.exception.ResourceNotFoundException;
import com.thiagomf.taskflowapi.repository.UserRepository;
import com.thiagomf.taskflowapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already registered");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return new AuthResponse(
                "User registered successfully",
                null,
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));

        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new ResourceNotFoundException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                "Login successful",
                token,
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}