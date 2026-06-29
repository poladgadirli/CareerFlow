package com.careerflow.auth;

import com.careerflow.auth.dto.AuthResponse;
import com.careerflow.auth.dto.LoginRequest;
import com.careerflow.auth.dto.RegisterRequest;
import com.careerflow.common.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return BaseResponse.success(
                "Registration successful",
                authService.register(request)
        );
    }

    @PostMapping("/login")
    public BaseResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return BaseResponse.success(
                "Login successful",
                authService.login(request)
        );
    }
}