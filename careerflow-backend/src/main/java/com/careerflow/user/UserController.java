package com.careerflow.user;

import com.careerflow.common.BaseResponse;
import com.careerflow.user.dto.SetFullNameRequest;
import com.careerflow.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;

    @GetMapping("/me")
    public BaseResponse<UserResponse> getCurrentUser() {
        User user = currentUserService.getCurrentUser();

        return BaseResponse.success(
                "User retrieved successfully",
                mapToResponse(user)
        );
    }

    @PatchMapping("/me/full-name")
    public BaseResponse<UserResponse> setFullName(
            @Valid @RequestBody SetFullNameRequest request
    ) {
        User user = currentUserService.getCurrentUser();

        user.setFullName(request.getFullName());

        User updatedUser = userRepository.save(user);

        return BaseResponse.success(
                "Full name updated successfully",
                mapToResponse(updatedUser)
        );
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