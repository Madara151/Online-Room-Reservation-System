package com.resort.reservation.controller;

import com.resort.reservation.dto.ApiResponseDTO;
import com.resort.reservation.dto.RoomTypeResponseDTO;
import com.resort.reservation.entity.RoomType;
import com.resort.reservation.service.RoomTypeService;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/room-types")
@CrossOrigin
public class RoomTypeController {


private final RoomTypeService roomTypeService;


public RoomTypeController(RoomTypeService roomTypeService) {
this.roomTypeService = roomTypeService;
}


@GetMapping
public ApiResponseDTO<List<RoomTypeResponseDTO>> getAll() {
List<RoomType> roomTypes = roomTypeService.getAllRoomTypes();


List<RoomTypeResponseDTO> dtoList = roomTypes.stream()
.map(rt -> new RoomTypeResponseDTO(rt.getId(), rt.getName(), rt.getRatePerNight()))
.toList();


return new ApiResponseDTO<>("Room types loaded", dtoList);
}
}