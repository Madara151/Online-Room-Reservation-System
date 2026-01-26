package com.resort.reservation.repository;

import com.resort.reservation.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
Optional<RoomType> findByName(String name);
}