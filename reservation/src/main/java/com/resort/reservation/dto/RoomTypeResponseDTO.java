package com.resort.reservation.dto;

import java.math.BigDecimal;


public class RoomTypeResponseDTO {


private Long id;
private String name;
private BigDecimal ratePerNight;


public RoomTypeResponseDTO() {}


public RoomTypeResponseDTO(Long id, String name, BigDecimal ratePerNight) {
this.id = id;
this.name = name;
this.ratePerNight = ratePerNight;
}


public Long getId() { return id; }
public String getName() { return name; }
public BigDecimal getRatePerNight() { return ratePerNight; }


public void setId(Long id) { this.id = id; }
public void setName(String name) { this.name = name; }
public void setRatePerNight(BigDecimal ratePerNight) { this.ratePerNight = ratePerNight; }
}