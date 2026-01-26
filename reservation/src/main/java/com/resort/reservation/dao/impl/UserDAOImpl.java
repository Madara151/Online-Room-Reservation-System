package com.resort.reservation.dao.impl;

import com.resort.reservation.dao.UserDAO;
import com.resort.reservation.entity.User;
import com.resort.reservation.repository.UserRepository;
import org.springframework.stereotype.Component;


import java.util.Optional;


@Component
public class UserDAOImpl implements UserDAO {


private final UserRepository userRepository;


public UserDAOImpl(UserRepository userRepository) {
this.userRepository = userRepository;
}


@Override
public Optional<User> findByUsername(String username) {
return userRepository.findByUsername(username);
}


@Override
public boolean existsByUsername(String username) {
return userRepository.existsByUsername(username);
}


@Override
public User save(User user) {
return userRepository.save(user);
}
}