package com.take_home_test.laodemurizalsabowaka.jawaban_practical_test.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DashboardSummary {
    private List<RoomUsageSummary> roomsSummary;
}
