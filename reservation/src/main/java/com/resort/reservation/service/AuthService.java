package com.resort.reservation.service;

import com.resort.reservation.dto.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO login(String username, String password);
    AuthResponseDTO register(String username, String password);
}