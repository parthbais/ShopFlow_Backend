package com.shopflow.service.impl;

import com.shopflow.dto.request.LoginRequest;
import com.shopflow.dto.request.RegisterRequest;
import com.shopflow.dto.response.UserResponse;
import com.shopflow.entity.User;
import com.shopflow.exception.DuplicateEmailException;
import com.shopflow.exception.ResourceNotFoundException;
import com.shopflow.mapper.UserMapper;
import com.shopflow.repository.UserRepository;
import com.shopflow.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository  userRepository;
    private final UserMapper      userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email already in use: " + request.getEmail());
        }
        User saved = userRepository.save(
                userMapper.toEntity(request, passwordEncoder.encode(request.getPassword())));
        log.info("User registered: id={}", saved.getId());
        return userMapper.toResponse(saved);
    }

    @Override
    public UserResponse login(LoginRequest request) {
        log.info("Login attempt: {}", request.getEmail());
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("No account found: " + request.getEmail()));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userMapper.toResponse(findOrThrow(id));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        findOrThrow(id);
        userRepository.deleteById(id);
        log.info("User deleted: id={}", id);
    }

    private User findOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
    }
}
