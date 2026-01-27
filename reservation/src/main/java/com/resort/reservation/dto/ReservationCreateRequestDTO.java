package com.resort.reservation.dto;

public class ReservationCreateRequestDTO {

    private String reservationNo;
    private String guestName;
    private String address;
    private String contactNo;
    private Long roomTypeId;
    private String checkIn;
    private String checkOut; 

    public ReservationCreateRequestDTO() {}

    public String getReservationNo() { return reservationNo; }
    public void setReservationNo(String reservationNo) { this.reservationNo = reservationNo; }

    public String getGuestName() { return guestName; }
    public void setGuestName(String guestName) { this.guestName = guestName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public Long getRoomTypeId() { return roomTypeId; }
    public void setRoomTypeId(Long roomTypeId) { this.roomTypeId = roomTypeId; }

    public String getCheckIn() { return checkIn; }
    public void setCheckIn(String checkIn) { this.checkIn = checkIn; }

    public String getCheckOut() { return checkOut; }
    public void setCheckOut(String checkOut) { this.checkOut = checkOut; }
}