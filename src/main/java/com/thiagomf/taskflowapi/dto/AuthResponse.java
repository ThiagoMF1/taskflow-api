package com.thiagomf.taskflowapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {
    private String message;
    private String name;
    private String email;
    private String role;
}