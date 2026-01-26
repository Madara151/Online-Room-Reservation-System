package com.resort.reservation.dto;

public class RevenueResponseDTO {
private String fromDate;
private String toDate;
private long totalBookings;
private String totalRevenue;


public RevenueResponseDTO() {}


public String getFromDate() { return fromDate; }
public String getToDate() { return toDate; }
public long getTotalBookings() { return totalBookings; }
public String getTotalRevenue() { return totalRevenue; }


public void setFromDate(String fromDate) { this.fromDate = fromDate; }
public void setToDate(String toDate) { this.toDate = toDate; }
public void setTotalBookings(long totalBookings) { this.totalBookings = totalBookings; }
public void setTotalRevenue(String totalRevenue) { this.totalRevenue = totalRevenue; }
}