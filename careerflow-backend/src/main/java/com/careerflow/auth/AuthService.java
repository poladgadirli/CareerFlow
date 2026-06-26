package com.careerflow.auth;

import com.careerflow.auth.dto.AuthResponse;
import com.careerflow.auth.dto.LoginRequest;
import com.careerflow.auth.dto.RegisterRequest;
import com.careerflow.security.JwtService;
import com.careerflow.user.Role;
import com.careerflow.user.User;
import com.careerflow.user.UserRepository;
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
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        String accessToken = jwtService.generateToken(user);

        return new AuthResponse(accessToken, "Bearer");
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getIdentifier())
                .or(() -> userRepository.findByUsername(request.getIdentifier()))
                .orElseThrow(() -> new RuntimeException("Invalid username/email or password"));

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        String accessToken = jwtService.generateToken(user);

        return new AuthResponse(accessToken, "Bearer");
    }
}