package com.resort.reservation.service;

import com.resort.reservation.entity.User;


public interface UserService {
User registerUser(String username, String rawPassword);
User login(String username, String rawPassword);
}