package com.resort.reservation.service.impl;

import com.resort.reservation.dao.ReservationDAO;
import com.resort.reservation.dao.RoomTypeDAO;
import com.resort.reservation.dto.ReservationCreateRequestDTO;
import com.resort.reservation.entity.Reservation;
import com.resort.reservation.entity.RoomType;
import com.resort.reservation.service.ReservationService;
import org.springframework.stereotype.Service;


import java.time.LocalDate;


@Service
public class ReservationServiceImpl implements ReservationService {


private final ReservationDAO reservationDAO;
private final RoomTypeDAO roomTypeDAO;


public ReservationServiceImpl(ReservationDAO reservationDAO, RoomTypeDAO roomTypeDAO) {
this.reservationDAO = reservationDAO;
this.roomTypeDAO = roomTypeDAO;
}


@Override
public Reservation createReservation(ReservationCreateRequestDTO request) {


if (reservationDAO.existsByReservationNo(request.getReservationNo())) {
throw new RuntimeException("Reservation number already exists");
}


RoomType roomType = roomTypeDAO.findById(request.getRoomTypeId())
.orElseThrow(() -> new RuntimeException("Room type not found"));


LocalDate checkIn;
LocalDate checkOut;

try {
    checkIn = LocalDate.parse(request.getCheckIn());
    checkOut = LocalDate.parse(request.getCheckOut());
} catch (Exception e) {
    throw new RuntimeException("Invalid date format. Use YYYY-MM-DD");
}

if (!checkOut.isAfter(checkIn)) {
    throw new RuntimeException("Check-out date must be after check-in date");
}


Reservation reservation = new Reservation();
reservation.setReservationNo(request.getReservationNo());
reservation.setGuestName(request.getGuestName());
reservation.setAddress(request.getAddress());
reservation.setContactNo(request.getContactNo());
reservation.setRoomType(roomType);
reservation.setCheckIn(checkIn);
reservation.setCheckOut(checkOut);


return reservationDAO.save(reservation);
}


@Override
public Reservation getReservationByNo(String reservationNo) {
return reservationDAO.findByReservationNo(reservationNo)
.orElseThrow(() -> new RuntimeException("Reservation not found"));
}
}