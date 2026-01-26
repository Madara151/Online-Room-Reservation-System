package com.resort.reservation.service.impl;

import com.resort.reservation.dto.ReservationCreateRequestDTO;
import com.resort.reservation.entity.Reservation;
import com.resort.reservation.entity.RoomType;
import com.resort.reservation.exception.BadRequestException;
import com.resort.reservation.exception.ConflictException;
import com.resort.reservation.exception.NotFoundException;
import com.resort.reservation.factory.ReservationNoFactory;
import com.resort.reservation.repository.ReservationRepository;
import com.resort.reservation.repository.RoomTypeRepository;
import com.resort.reservation.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final ReservationNoFactory reservationNoFactory;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  RoomTypeRepository roomTypeRepository,
                                  ReservationNoFactory reservationNoFactory) {
        this.reservationRepository = reservationRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.reservationNoFactory = reservationNoFactory;
    }

    @Override
    public Reservation createReservation(ReservationCreateRequestDTO request) {

        if (request == null) throw new BadRequestException("Request body is missing");

        String guestName = safe(request.getGuestName());
        String address = safe(request.getAddress());
        String contact = safe(request.getContactNo());

        if (guestName.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            throw new BadRequestException("Guest name, address and contact number are required");
        }

        if (!contact.matches("^[0-9]{10}$")) {
            throw new BadRequestException("Contact number must be 10 digits (e.g., 0771234567)");
        }

        if (request.getRoomTypeId() == null) {
            throw new BadRequestException("Room type is required");
        }

        LocalDate checkIn;
        LocalDate checkOut;

        try {
            checkIn = LocalDate.parse(safe(request.getCheckIn()));
            checkOut = LocalDate.parse(safe(request.getCheckOut()));
        } catch (Exception e) {
            throw new BadRequestException("Invalid date format. Use YYYY-MM-DD");
        }

        if (!checkOut.isAfter(checkIn)) {
            throw new BadRequestException("Check-out date must be after check-in date");
        }

        RoomType roomType = roomTypeRepository.findById(request.getRoomTypeId())
                .orElseThrow(() -> new NotFoundException("Room type not found"));

        // Reservation No: allow user input, but generate if empty (Factory pattern)
        String reservationNo = safe(request.getReservationNo());
        if (reservationNo.isEmpty()) {
            reservationNo = reservationNoFactory.generate();
        }

        if (reservationRepository.existsByReservationNo(reservationNo)) {
            throw new ConflictException("Reservation number already exists: " + reservationNo);
        }

        Reservation reservation = new Reservation();
        reservation.setReservationNo(reservationNo);
        reservation.setGuestName(guestName);
        reservation.setAddress(address);
        reservation.setContactNo(contact);
        reservation.setRoomType(roomType);
        reservation.setCheckIn(checkIn);
        reservation.setCheckOut(checkOut);

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservationByNo(String reservationNo) {
        String no = safe(reservationNo);
        if (no.isEmpty()) throw new BadRequestException("Reservation number is required");

        return reservationRepository.findByReservationNo(no)
                .orElseThrow(() -> new NotFoundException("Reservation not found: " + no));
    }

    private String safe(String s) {
        return s == null ? "" : s.trim();
    }
}