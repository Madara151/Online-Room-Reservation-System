package com.resort.reservation.controller;

import com.resort.reservation.dto.ApiResponseDTO;
import com.resort.reservation.dto.BillResponseDTO;
import com.resort.reservation.service.BillingService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/billing")
@CrossOrigin
public class BillingController {


private final BillingService billingService;


public BillingController(BillingService billingService) {
this.billingService = billingService;
}


@GetMapping("/{reservationNo}")
public ApiResponseDTO<BillResponseDTO> generateBill(@PathVariable String reservationNo) {
BillResponseDTO bill = billingService.generateBill(reservationNo);
return new ApiResponseDTO<>("Bill generated", bill);
}
}