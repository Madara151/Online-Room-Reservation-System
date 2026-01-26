package com.resort.reservation.dao.impl;

import com.resort.reservation.dao.ReservationDAO;
import com.resort.reservation.entity.Reservation;
import com.resort.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Component;


import java.util.Optional;


@Component
public class ReservationDAOImpl implements ReservationDAO {


private final ReservationRepository reservationRepository;


public ReservationDAOImpl(ReservationRepository reservationRepository) {
this.reservationRepository = reservationRepository;
}


@Override
public Reservation save(Reservation reservation) {
return reservationRepository.save(reservation);
}


@Override
public Optional<Reservation> findByReservationNo(String reservationNo) {
return reservationRepository.findByReservationNo(reservationNo);
}


@Override
public boolean existsByReservationNo(String reservationNo) {
return reservationRepository.existsByReservationNo(reservationNo);
}
}