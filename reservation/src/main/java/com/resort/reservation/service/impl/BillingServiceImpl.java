package com.resort.reservation.service.impl;

import com.resort.reservation.dto.BillResponseDTO;
import com.resort.reservation.entity.Reservation;
import com.resort.reservation.service.BillingService;
import com.resort.reservation.service.ReservationService;
import org.springframework.stereotype.Service;


import java.time.temporal.ChronoUnit;


@Service
public class BillingServiceImpl implements BillingService {


private final ReservationService reservationService;


public BillingServiceImpl(ReservationService reservationService) {
this.reservationService = reservationService;
}


@Override
public BillResponseDTO generateBill(String reservationNo) {


Reservation reservation = reservationService.getReservationByNo(reservationNo);


long nights = ChronoUnit.DAYS.between(
reservation.getCheckIn(),
reservation.getCheckOut()
);


var rate = reservation.getRoomType().getRatePerNight();
var total = rate.multiply(java.math.BigDecimal.valueOf(nights));


BillResponseDTO bill = new BillResponseDTO();
bill.setReservationNo(reservationNo);
bill.setGuestName(reservation.getGuestName());
bill.setRoomTypeName(reservation.getRoomType().getName());
bill.setNights(nights);
bill.setRatePerNight(rate.toString());
bill.setTotalAmount(total.toString());


return bill;
}
}