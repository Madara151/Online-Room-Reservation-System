package com.resort.reservation.service;

import com.resort.reservation.dto.ReservationCreateRequestDTO;
import com.resort.reservation.entity.Reservation;

public interface ReservationService {
    Reservation createReservation(ReservationCreateRequestDTO request);
    Reservation getReservationByNo(String reservationNo);
}