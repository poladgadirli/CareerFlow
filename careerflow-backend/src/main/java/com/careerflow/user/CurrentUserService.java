package com.careerflow.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserService {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Unauthorized");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof User user && user.getId() != null) {
            return userRepository.findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        String usernameOrEmail = switch (principal) {
            case UserDetails userDetails -> userDetails.getUsername();
            case String value -> value;
            default -> authentication.getName();
        };

        if (usernameOrEmail == null || "anonymousUser".equals(usernameOrEmail)) {
            throw new RuntimeException("Unauthorized");
        }

        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
