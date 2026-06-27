package com.careerflow.user.dto;

import com.careerflow.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserResponse {

    private UUID id;
    private String username;
    private String email;
    private String fullName;
    private Role role;
}