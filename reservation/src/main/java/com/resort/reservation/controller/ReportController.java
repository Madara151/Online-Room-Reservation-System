package com.resort.reservation.controller;

import com.resort.reservation.dto.ApiResponseDTO;
import com.resort.reservation.dto.ReservationResponseDTO;
import com.resort.reservation.dto.RevenueResponseDTO;
import com.resort.reservation.service.ReportService;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class ReportController {


private final ReportService reportService;


public ReportController(ReportService reportService) {
this.reportService = reportService;
}


// ✅ Bookings by date range
// Example: /api/reports/bookings?from=2026-01-01&to=2026-01-31
@GetMapping("/bookings")
public ApiResponseDTO<List<ReservationResponseDTO>> bookings(
@RequestParam String from,
@RequestParam String to
) {
return new ApiResponseDTO<>(
"Bookings loaded",
reportService.getBookingsByDateRange(from, to)
);
}


// ✅ Revenue by date range
// Example: /api/reports/revenue?from=2026-01-01&to=2026-01-31
@GetMapping("/revenue")
public ApiResponseDTO<RevenueResponseDTO> revenue(
@RequestParam String from,
@RequestParam String to
) {
return new ApiResponseDTO<>(
"Revenue calculated",
reportService.getRevenueByDateRange(from, to)
);
}
}