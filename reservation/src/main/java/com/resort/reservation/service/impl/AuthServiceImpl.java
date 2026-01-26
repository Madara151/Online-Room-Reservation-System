package com.resort.reservation.service.impl;

import com.resort.reservation.dto.AuthResponseDTO;
import com.resort.reservation.entity.User;
import com.resort.reservation.security.JwtUtil;
import com.resort.reservation.service.AuthService;
import com.resort.reservation.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public AuthResponseDTO login(String username, String password) {
        User user = userService.login(username, password);

        String token = JwtUtil.generateToken(user.getUsername(), user.getRole());

        AuthResponseDTO dto = new AuthResponseDTO();
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        dto.setToken(token);
        return dto;
    }

    @Override
    public AuthResponseDTO register(String username, String password) {
        User user = userService.registerUser(username, password);

        String token = JwtUtil.generateToken(user.getUsername(), user.getRole());

        AuthResponseDTO dto = new AuthResponseDTO();
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        dto.setToken(token);
        return dto;
    }
}