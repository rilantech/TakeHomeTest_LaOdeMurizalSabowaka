package com.take_home_test.laodemurizalsabowaka.jawaban_practical_test.controller;

import com.take_home_test.laodemurizalsabowaka.jawaban_practical_test.model.DashboardSummary;
import com.take_home_test.laodemurizalsabowaka.jawaban_practical_test.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService service;

    @GetMapping("/summary")
    public DashboardSummary getSummary(@RequestParam int month,  @RequestParam int year) {
        return service.getDashboardSummary(month, year);
    }
}
