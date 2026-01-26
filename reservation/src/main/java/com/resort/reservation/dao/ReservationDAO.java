package com.resort.reservation.dao;

import com.resort.reservation.entity.Reservation;
import java.util.Optional;


public interface ReservationDAO {
Reservation save(Reservation reservation);
Optional<Reservation> findByReservationNo(String reservationNo);
boolean existsByReservationNo(String reservationNo);
}