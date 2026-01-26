package com.resort.reservation.dao;

import com.resort.reservation.entity.User;
import java.util.Optional;


public interface UserDAO {
Optional<User> findByUsername(String username);
boolean existsByUsername(String username);
User save(User user);
}