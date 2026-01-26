package com.resort.reservation.dto;

public class BillResponseDTO {


private String reservationNo;
private String guestName;
private String roomTypeName;


private long nights;
private String ratePerNight;
private String totalAmount;


public BillResponseDTO() {}


public String getReservationNo() { return reservationNo; }
public String getGuestName() { return guestName; }
public String getRoomTypeName() { return roomTypeName; }
public long getNights() { return nights; }
public String getRatePerNight() { return ratePerNight; }
public String getTotalAmount() { return totalAmount; }


public void setReservationNo(String reservationNo) { this.reservationNo = reservationNo; }
public void setGuestName(String guestName) { this.guestName = guestName; }
public void setRoomTypeName(String roomTypeName) { this.roomTypeName = roomTypeName; }
public void setNights(long nights) { this.nights = nights; }
public void setRatePerNight(String ratePerNight) { this.ratePerNight = ratePerNight; }
public void setTotalAmount(String totalAmount) { this.totalAmount = totalAmount; }
}