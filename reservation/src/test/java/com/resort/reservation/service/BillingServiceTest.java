package com.resort.reservation.service;

import com.resort.reservation.dto.BillResponseDTO;
import com.resort.reservation.entity.Reservation;
import com.resort.reservation.entity.RoomType;
import com.resort.reservation.service.impl.BillingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BillingServiceTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private BillingServiceImpl billingService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateBill_shouldCalculateCorrectFields() {

        // Arrange
        RoomType rt = new RoomType();
        rt.setName("Deluxe");
        rt.setRatePerNight(new BigDecimal("10000"));

        Reservation r = new Reservation();
        r.setReservationNo("R001");
        r.setGuestName("John");
        r.setRoomType(rt);
        r.setCheckIn(LocalDate.parse("2026-01-26"));
        r.setCheckOut(LocalDate.parse("2026-01-28")); // 2 nights

        when(reservationService.getReservationByNo("R001")).thenReturn(r);

        // Act
        BillResponseDTO bill = billingService.generateBill("R001");

        // Assert
        assertNotNull(bill);
        assertEquals("R001", bill.getReservationNo());
        assertEquals("John", bill.getGuestName());
        assertEquals("Deluxe", bill.getRoomTypeName());

        // nights is long in your BillResponseDTO
        assertEquals(2L, bill.getNights());

        // Your service sets Strings
        assertEquals("10000", bill.getRatePerNight());
        assertEquals("20000", bill.getTotalAmount());

        verify(reservationService, times(1)).getReservationByNo("R001");
    }

    @Test
    void generateBill_shouldThrow_whenReservationServiceThrows() {
        when(reservationService.getReservationByNo("R999"))
                .thenThrow(new RuntimeException("Reservation not found"));

        assertThrows(RuntimeException.class, () -> billingService.generateBill("R999"));
    }
}