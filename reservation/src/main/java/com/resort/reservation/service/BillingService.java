package com.resort.reservation.service;

import com.resort.reservation.dto.BillResponseDTO;


public interface BillingService {
BillResponseDTO generateBill(String reservationNo);
}