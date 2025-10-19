package com.take_home_test.laodemurizalsabowaka.jawaban_practical_test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomUsageSummary {
    private String unitInduk;
    private String roomName;
    private double usagePercent;
    private double nominalKonsumsi;
    private long countSnackSiang;
    private long countMakanSiang;
    private long countSnackSore;
}
