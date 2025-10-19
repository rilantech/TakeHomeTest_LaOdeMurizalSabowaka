package com.take_home_test.laodemurizalsabowaka.jawaban_practical_test.service.impl;

import com.take_home_test.laodemurizalsabowaka.jawaban_practical_test.model.Booking;
import com.take_home_test.laodemurizalsabowaka.jawaban_practical_test.model.DashboardSummary;
import com.take_home_test.laodemurizalsabowaka.jawaban_practical_test.model.MasterKonsumsi;
import com.take_home_test.laodemurizalsabowaka.jawaban_practical_test.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final RestTemplate restTemplate;
    private final String bookingApiUrl = "https://66876cc30bc7155dc017a662.mockapi.io/api/dummy-data/bookingList";
    private final String konsumsiApiUrl = "https://6686cb5583c983911b03a7f3.mockapi.io/api/dummy-data/masterJenisKonsumsi";
    @Override
    public DashboardSummary getDashboardSummary(int month, int year) {
        Booking[] bookings = restTemplate.getForObject(bookingApiUrl, Booking[].class);
        MasterKonsumsi[] masterKs = restTemplate.getForObject(konsumsiApiUrl, MasterKonsumsi[].class);

        if (bookings == null) bookings = new Booking[0];
        if (masterKs == null) masterKs = new MasterKonsumsi[0];

        YearMonth targetYm = YearMonth.of(year, month);
        List<Booking> filtered = Arrays.stream(bookings)
                .filter(b -> b.getStartTime() != null)
                .filter(b -> {
                    YearMonth ym = YearMonth.from(b.getStartTime().toLocalDate());
                    return ym.equals(targetYm);
                })
                .collect(Collectors.toList());

        return null;
    }
}
