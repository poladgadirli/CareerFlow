package com.careerflow.user;

import com.careerflow.user.dto.SetFullNameRequest;
import com.careerflow.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public UserResponse getCurrentUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return mapToResponse(user);
    }

    @PatchMapping("/me/full-name")
    public UserResponse setFullName(
            Authentication authentication,
            @Valid @RequestBody SetFullNameRequest request
    ) {
        User user = (User) authentication.getPrincipal();

        user.setFullName(request.getFullName());

        User updatedUser = userRepository.save(user);

        return mapToResponse(updatedUser);
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFullName(),
                user.getRole()
        );
    }
}