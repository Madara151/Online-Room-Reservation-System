package com.resort.reservation.service;

import com.resort.reservation.entity.RoomType;
import java.util.List;


public interface RoomTypeService {
List<RoomType> getAllRoomTypes();
RoomType getRoomTypeById(Long id);
}