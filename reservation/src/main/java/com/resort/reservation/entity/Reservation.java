package com.resort.reservation.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Instant;


@Entity
@Table(name = "reservations")
public class Reservation {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@Column(name = "reservation_no", nullable = false, unique = true, length = 30)
private String reservationNo;


@Column(name = "guest_name", nullable = false, length = 100)
private String guestName;


@Column(nullable = false, length = 255)
private String address;


@Column(name = "contact_no", nullable = false, length = 20)
private String contactNo;


@ManyToOne(optional = false, fetch = FetchType.LAZY)
@JoinColumn(name = "room_type_id", nullable = false)
private RoomType roomType;


@Column(name = "check_in", nullable = false)
private LocalDate checkIn;


@Column(name = "check_out", nullable = false)
private LocalDate checkOut;


@Column(name = "created_at", nullable = false, updatable = false)
private Instant createdAt;


@PrePersist
void onCreate() {
this.createdAt = Instant.now();
}


public Reservation() {}


// getters
public Long getId() { return id; }
public String getReservationNo() { return reservationNo; }
public String getGuestName() { return guestName; }
public String getAddress() { return address; }
public String getContactNo() { return contactNo; }
public RoomType getRoomType() { return roomType; }
public LocalDate getCheckIn() { return checkIn; }
public LocalDate getCheckOut() { return checkOut; }
public Instant getCreatedAt() { return createdAt; }


// setters
public void setId(Long id) { this.id = id; }
public void setReservationNo(String reservationNo) { this.reservationNo = reservationNo; }
public void setGuestName(String guestName) { this.guestName = guestName; }
public void setAddress(String address) { this.address = address; }
public void setContactNo(String contactNo) { this.contactNo = contactNo; }
public void setRoomType(RoomType roomType) { this.roomType = roomType; }
public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }
public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }
}