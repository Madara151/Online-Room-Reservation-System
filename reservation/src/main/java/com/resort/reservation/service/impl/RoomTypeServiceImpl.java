package com.resort.reservation.service.impl;

import com.resort.reservation.dao.RoomTypeDAO;
import com.resort.reservation.entity.RoomType;
import com.resort.reservation.service.RoomTypeService;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class RoomTypeServiceImpl implements RoomTypeService {


private final RoomTypeDAO roomTypeDAO;


public RoomTypeServiceImpl(RoomTypeDAO roomTypeDAO) {
this.roomTypeDAO = roomTypeDAO;
}


@Override
public List<RoomType> getAllRoomTypes() {
return roomTypeDAO.findAll();
}


@Override
public RoomType getRoomTypeById(Long id) {
return roomTypeDAO.findById(id)
.orElseThrow(() -> new RuntimeException("Room type not found"));
}
}