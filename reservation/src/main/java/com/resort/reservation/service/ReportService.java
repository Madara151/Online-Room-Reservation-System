package com.resort.reservation.service;

import com.resort.reservation.dto.ReservationResponseDTO;
import com.resort.reservation.dto.RevenueResponseDTO;


import java.util.List;


public interface ReportService {
List<ReservationResponseDTO> getBookingsByDateRange(String from, String to);
RevenueResponseDTO getRevenueByDateRange(String from, String to);
}