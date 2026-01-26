package com.resort.reservation.dto;

public class ReservationResponseDTO {


private String reservationNo;
private String guestName;
private String address;
private String contactNo;


private Long roomTypeId;
private String roomTypeName;
private String ratePerNight;


private String checkIn;
private String checkOut;


public ReservationResponseDTO() {}


public String getReservationNo() { return reservationNo; }
public String getGuestName() { return guestName; }
public String getAddress() { return address; }
public String getContactNo() { return contactNo; }
public Long getRoomTypeId() { return roomTypeId; }
public String getRoomTypeName() { return roomTypeName; }
public String getRatePerNight() { return ratePerNight; }
public String getCheckIn() { return checkIn; }
public String getCheckOut() { return checkOut; }


public void setReservationNo(String reservationNo) { this.reservationNo = reservationNo; }
public void setGuestName(String guestName) { this.guestName = guestName; }
public void setAddress(String address) { this.address = address; }
public void setContactNo(String contactNo) { this.contactNo = contactNo; }
public void setRoomTypeId(Long roomTypeId) { this.roomTypeId = roomTypeId; }
public void setRoomTypeName(String roomTypeName) { this.roomTypeName = roomTypeName; }
public void setRatePerNight(String ratePerNight) { this.ratePerNight = ratePerNight; }
public void setCheckIn(String checkIn) { this.checkIn = checkIn; }
public void setCheckOut(String checkOut) { this.checkOut = checkOut; }
}