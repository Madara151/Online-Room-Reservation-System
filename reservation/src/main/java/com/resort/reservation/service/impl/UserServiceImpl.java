package com.resort.reservation.service.impl;

import com.resort.reservation.dao.UserDAO;
import com.resort.reservation.entity.User;
import com.resort.reservation.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


private final UserDAO userDAO;
private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


public UserServiceImpl(UserDAO userDAO) {
this.userDAO = userDAO;
}


@Override
public User registerUser(String username, String rawPassword) {


if (userDAO.existsByUsername(username)) {
throw new RuntimeException("Username already exists");
}


String hashedPassword = passwordEncoder.encode(rawPassword);


User user = new User(username, hashedPassword, "STAFF");


return userDAO.save(user);
}


@Override
public User login(String username, String rawPassword) {


User user = userDAO.findByUsername(username)
.orElseThrow(() -> new RuntimeException("Invalid username"));


if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
throw new RuntimeException("Invalid password");
}


return user;
}
}