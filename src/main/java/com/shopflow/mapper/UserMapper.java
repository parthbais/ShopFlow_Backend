package com.shopflow.mapper;

import com.shopflow.config.AppConstants;
import com.shopflow.dto.request.RegisterRequest;
import com.shopflow.dto.response.UserResponse;
import com.shopflow.entity.Role;
import com.shopflow.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public User toEntity(RegisterRequest request, String encodedPassword) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                .role(resolveRole(request.getRole()))
                .build();
    }

    private Role resolveRole(String roleStr) {
        if (roleStr == null || roleStr.isBlank()) return Role.valueOf(AppConstants.DEFAULT_ROLE);
        try {
            return Role.valueOf(roleStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Role.valueOf(AppConstants.DEFAULT_ROLE);
        }
    }
}
