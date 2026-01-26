package com.resort.reservation.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "room_types")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "rate_per_night", nullable = false, precision = 10, scale = 2)
    private BigDecimal ratePerNight;

    public RoomType() {}

    public RoomType(String name, BigDecimal ratePerNight) {
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