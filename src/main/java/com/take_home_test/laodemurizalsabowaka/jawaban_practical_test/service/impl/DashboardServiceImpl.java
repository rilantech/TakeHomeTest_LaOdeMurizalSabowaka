package com.take_home_test.laodemurizalsabowaka.jawaban_practical_test.service.impl;

import com.take_home_test.laodemurizalsabowaka.jawaban_practical_test.model.*;
import com.take_home_test.laodemurizalsabowaka.jawaban_practical_test.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.YearMonth;
import java.util.*;
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

        Map<String, String> officeToUnit = Map.of(
                "UID JAYA", "Unit Induk 1",
                "UID BALI", "Unit Induk 2",
                "UID JABAR", "Unit Induk 3",
                "UID JATENG & DIY", "Unit Induk 4",
                "UID JATIM", "Unit Induk 5"
        );

        Map<String, Double> hargaKonsumsiByName = Arrays.stream(masterKs)
                .collect(Collectors.toMap(MasterKonsumsi::getName, MasterKonsumsi::getMaxPrice, (a, b) -> a));

        List<RoomUsageSummary> roomsSummary = new ArrayList<>();

        for (Booking b : filtered) {
            String unit = officeToUnit.getOrDefault(b.getOfficeName(), "Unit Lain");
            String room = b.getRoomName();

            Optional<RoomUsageSummary> opt = roomsSummary.stream()
                    .filter(r -> r.getUnitInduk().equals(unit) && r.getRoomName().equals(room))
                    .findFirst();

            RoomUsageSummary summary = opt.orElseGet(() -> {
                RoomUsageSummary r = new RoomUsageSummary();
                r.setUnitInduk(unit);
                r.setRoomName(room);
                r.setUsagePercent(0.0);
                r.setNominalKonsumsi(0.0);
                r.setCountSnackSiang(0);
                r.setCountMakanSiang(0);
                r.setCountSnackSore(0L);
                roomsSummary.add(r);
                roomsSummary.sort(Comparator.comparingInt(summari->
                        extractUnitNumber(summari.getUnitInduk())));
                return r;
            });

            if (b.getListConsumption() != null) {
                for (ConsumptionItem ci : b.getListConsumption()) {
                    String nama = ci.getName();
                    Double harga = hargaKonsumsiByName.getOrDefault(nama, 0.0);
                    summary.setNominalKonsumsi(summary.getNominalKonsumsi() + harga);

                    if (nama.equalsIgnoreCase("Snack Siang")) {
                        summary.setCountSnackSiang(summary.getCountSnackSiang() + 1);
                    } else if (nama.equalsIgnoreCase("Makan Siang")) {
                        summary.setCountMakanSiang(summary.getCountMakanSiang() + 1);
                    } else if (nama.equalsIgnoreCase("Snack Sore")) {
                        summary.setCountSnackSore(summary.getCountSnackSore() + 1);
                    }
                }
            }

            long durationHours = java.time.Duration.between(b.getStartTime(), b.getEndTime()).toHours();
            summary.setUsagePercent(summary.getUsagePercent() + durationHours);
        }

        double jamMaxDalamBulan = 10.31 * 12;
        for (RoomUsageSummary rs : roomsSummary) {
            double jamDipakai = rs.getUsagePercent();
            double percent = (jamDipakai / jamMaxDalamBulan) * 100.0;
            rs.setUsagePercent(percent);
        }

        DashboardSummary dashboard = new DashboardSummary();
        dashboard.setRoomsSummary(roomsSummary);
        return dashboard;
    }

    private int extractUnitNumber(String unitInduk) {
        try {
            return Integer.parseInt(unitInduk.replaceAll("\\D+", ""));
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
}
