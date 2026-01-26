package com.resort.reservation.dao;

import com.resort.reservation.entity.RoomType;
import java.util.List;
import java.util.Optional;


public interface RoomTypeDAO {
List<RoomType> findAll();
Optional<RoomType> findById(Long id);
Optional<RoomType> findByName(String name);
}