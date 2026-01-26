package com.resort.reservation.factory;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ReservationNoFactory {

    private final AtomicInteger counter = new AtomicInteger(1);

    public String generate() {
        return String.format("R%03d", counter.getAndIncrement());
    }
}