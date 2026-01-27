package com.resort.reservation.service;

import com.resort.reservation.dto.ReservationCreateRequestDTO;
import com.resort.reservation.entity.Reservation;
import com.resort.reservation.entity.RoomType;
import com.resort.reservation.exception.BadRequestException;
import com.resort.reservation.exception.ConflictException;
import com.resort.reservation.exception.NotFoundException;
import com.resort.reservation.factory.ReservationNoFactory;
import com.resort.reservation.repository.ReservationRepository;
import com.resort.reservation.repository.RoomTypeRepository;
import com.resort.reservation.service.impl.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservationServiceTest {

    @Mock private ReservationRepository reservationRepository;
    @Mock private RoomTypeRepository roomTypeRepository;
    @Mock private ReservationNoFactory reservationNoFactory;

    @InjectMocks private ReservationServiceImpl reservationService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private ReservationCreateRequestDTO buildReq(String no, String in, String out) {
        ReservationCreateRequestDTO req = new ReservationCreateRequestDTO();
        req.setReservationNo(no);
        req.setGuestName("John");
        req.setAddress("Galle");
        req.setContactNo("0771234567");
        req.setRoomTypeId(1L);
        req.setCheckIn(in);
        req.setCheckOut(out);
        return req;
    }

    @Test
    void createReservation_shouldThrowConflict_whenReservationNoExists() {
        ReservationCreateRequestDTO req = buildReq("R001", "2026-01-26", "2026-01-28");

        // ✅ Must mock room type fetch (otherwise NotFoundException happens first)
        RoomType rt = new RoomType();
        rt.setId(1L);
        rt.setName("Deluxe");
        rt.setRatePerNight(new BigDecimal("10000"));
        when(roomTypeRepository.findById(1L)).thenReturn(Optional.of(rt));

        // ✅ Your service checks existsByReservationNo()
        when(reservationRepository.existsByReservationNo("R001")).thenReturn(true);

        assertThrows(ConflictException.class, () -> reservationService.createReservation(req));
    }

    @Test
    void createReservation_shouldThrowBadRequest_whenCheckoutBeforeCheckin() {
        ReservationCreateRequestDTO req = buildReq("R002", "2026-01-28", "2026-01-26");

        assertThrows(BadRequestException.class, () -> reservationService.createReservation(req));
    }

    @Test
    void createReservation_shouldSave_whenValidData() {
        ReservationCreateRequestDTO req = buildReq("R003", "2026-01-26", "2026-01-28");

        RoomType rt = new RoomType();
        rt.setId(1L);
        rt.setName("Deluxe");
        rt.setRatePerNight(new BigDecimal("10000"));
        when(roomTypeRepository.findById(1L)).thenReturn(Optional.of(rt));

        when(reservationRepository.existsByReservationNo("R003")).thenReturn(false);

        when(reservationRepository.save(any(Reservation.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        Reservation saved = reservationService.createReservation(req);

        assertNotNull(saved);
        assertEquals("R003", saved.getReservationNo());
        assertEquals(LocalDate.parse("2026-01-26"), saved.getCheckIn());
        assertEquals(LocalDate.parse("2026-01-28"), saved.getCheckOut());
        assertNotNull(saved.getRoomType());

        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void findReservation_shouldThrowNotFound_whenMissing() {
        when(reservationRepository.findByReservationNo("R999"))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> reservationService.getReservationByNo("R999"));
    }
}