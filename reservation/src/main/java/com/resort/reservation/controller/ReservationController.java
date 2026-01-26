package com.resort.reservation.controller;

import com.resort.reservation.dto.*;
import com.resort.reservation.entity.Reservation;
import com.resort.reservation.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/reservations")
@CrossOrigin
public class ReservationController {


private final ReservationService reservationService;


public ReservationController(ReservationService reservationService) {
this.reservationService = reservationService;
}


// Add reservation
@PostMapping
public ApiResponseDTO<String> create(@RequestBody @Valid ReservationCreateRequestDTO req) {
Reservation saved = reservationService.createReservation(req);
return new ApiResponseDTO<>("Reservation created", saved.getReservationNo());
}


// Get reservation by reservationNo
@GetMapping("/{reservationNo}")
public ApiResponseDTO<ReservationResponseDTO> getByNo(@PathVariable String reservationNo) {


Reservation r = reservationService.getReservationByNo(reservationNo);


ReservationResponseDTO dto = new ReservationResponseDTO();
dto.setReservationNo(r.getReservationNo());
dto.setGuestName(r.getGuestName());
dto.setAddress(r.getAddress());
dto.setContactNo(r.getContactNo());
dto.setRoomTypeId(r.getRoomType().getId());
dto.setRoomTypeName(r.getRoomType().getName());
dto.setRatePerNight(r.getRoomType().getRatePerNight().toString());
dto.setCheckIn(r.getCheckIn().toString());
dto.setCheckOut(r.getCheckOut().toString());


return new ApiResponseDTO<>("Reservation found", dto);
}
}