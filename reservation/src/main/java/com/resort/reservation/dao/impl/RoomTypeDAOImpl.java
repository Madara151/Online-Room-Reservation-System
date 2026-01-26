package com.resort.reservation.dao.impl;

import com.resort.reservation.dao.RoomTypeDAO;
import com.resort.reservation.entity.RoomType;
import com.resort.reservation.repository.RoomTypeRepository;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;


@Component
public class RoomTypeDAOImpl implements RoomTypeDAO {


private final RoomTypeRepository roomTypeRepository;


public RoomTypeDAOImpl(RoomTypeRepository roomTypeRepository) {
this.roomTypeRepository = roomTypeRepository;
}


@Override
public List<RoomType> findAll() {
return roomTypeRepository.findAll();
}


@Override
public Optional<RoomType> findById(Long id) {
return roomTypeRepository.findById(id);
}


@Override
public Optional<RoomType> findByName(String name) {
return roomTypeRepository.findByName(name);
}
}