package com.resort.reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public class ReservationCreateRequestDTO {


@NotBlank(message = "Reservation number is required")
private String reservationNo;


@NotBlank(message = "Guest name is required")
private String guestName;


@NotBlank(message = "Address is required")
private String address;


@NotBlank(message = "Contact number is required")
@Pattern(regexp = "^[0-9+]{9,15}$", message = "Contact number must be 9-15 digits (you can include +)")
private String contactNo;


@NotNull(message = "Room type ID is required")
private Long roomTypeId;


@NotBlank(message = "Check-in date is required (YYYY-MM-DD)")
private String checkIn;


@NotBlank(message = "Check-out date is required (YYYY-MM-DD)")
private String checkOut;


public ReservationCreateRequestDTO() {}


public String getReservationNo() { return reservationNo; }
public String getGuestName() { return guestName; }
public String getAddress() { return address; }
public String getContactNo() { return contactNo; }
public Long getRoomTypeId() { return roomTypeId; }
public String getCheckIn() { return checkIn; }
public String getCheckOut() { return checkOut; }


public void setReservationNo(String reservationNo) { this.reservationNo = reservationNo; }
public void setGuestName(String guestName) { this.guestName = guestName; }
public void setAddress(String address) { this.address = address; }
public void setContactNo(String contactNo) { this.contactNo = contactNo; }
public void setRoomTypeId(Long roomTypeId) { this.roomTypeId = roomTypeId; }
public void setCheckIn(String checkIn) { this.checkIn = checkIn; }
public void setCheckOut(String checkOut) { this.checkOut = checkOut; }
}