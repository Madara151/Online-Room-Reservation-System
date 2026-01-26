package com.resort.reservation.repository;

import com.resort.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {


java.util.Optional<Reservation> findByReservationNo(String reservationNo);
boolean existsByReservationNo(String reservationNo);


// ✅ Bookings in date range (check_in between)
@Query("SELECT r FROM Reservation r WHERE r.checkIn BETWEEN :from AND :to ORDER BY r.checkIn ASC")
List<Reservation> findBookingsBetween(@Param("from") LocalDate from, @Param("to") LocalDate to);


// ✅ Bookings overlapping date range (better)
@Query("""
SELECT r FROM Reservation r
WHERE r.checkIn < :to AND r.checkOut > :from
ORDER BY r.checkIn ASC
""")
List<Reservation> findBookingsOverlapping(@Param("from") LocalDate from, @Param("to") LocalDate to);
}