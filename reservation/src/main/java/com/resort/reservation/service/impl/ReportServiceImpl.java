package com.resort.reservation.service.impl;

import com.resort.reservation.dto.ReservationResponseDTO;
import com.resort.reservation.dto.RevenueResponseDTO;
import com.resort.reservation.entity.Reservation;
import com.resort.reservation.repository.ReservationRepository;
import com.resort.reservation.service.ReportService;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class ReportServiceImpl implements ReportService {


private final ReservationRepository reservationRepository;


public ReportServiceImpl(ReservationRepository reservationRepository) {
this.reservationRepository = reservationRepository;
}


@Override
public List<ReservationResponseDTO> getBookingsByDateRange(String from, String to) {


LocalDate fromDate = LocalDate.parse(from);
LocalDate toDate = LocalDate.parse(to);


List<Reservation> reservations =
reservationRepository.findBookingsOverlapping(fromDate, toDate);


return reservations.stream().map(r -> {
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
return dto;
}).toList();
}


@Override
public RevenueResponseDTO getRevenueByDateRange(String from, String to) {


LocalDate fromDate = LocalDate.parse(from);
LocalDate toDate = LocalDate.parse(to);


List<Reservation> reservations =
reservationRepository.findBookingsOverlapping(fromDate, toDate);


BigDecimal totalRevenue = BigDecimal.ZERO;


for (Reservation r : reservations) {
long nights = ChronoUnit.DAYS.between(r.getCheckIn(), r.getCheckOut());
BigDecimal bookingTotal = r.getRoomType().getRatePerNight()
.multiply(BigDecimal.valueOf(nights));
totalRevenue = totalRevenue.add(bookingTotal);
}


RevenueResponseDTO dto = new RevenueResponseDTO();
dto.setFromDate(from);
dto.setToDate(to);
dto.setTotalBookings(reservations.size());
dto.setTotalRevenue(totalRevenue.toString());
return dto;
}
}