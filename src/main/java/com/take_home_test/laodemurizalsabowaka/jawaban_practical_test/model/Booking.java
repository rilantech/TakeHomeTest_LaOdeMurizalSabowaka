package com.take_home_test.laodemurizalsabowaka.jawaban_practical_test.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class Booking {
    private String id;
    private String officeName;
    private String roomName;
    private LocalDateTime bookingDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int participants;
    private List<ConsumptionItem> listConsumption;
}
