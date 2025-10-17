package com.take_home_test.laodemurizalsabowaka.jawaban_logical_test;

public class SoalNomor5 {
    public static void main(String[] args) {
        String[] soal1  = {"b", "7", "h", "6", "h", "k", "i", "5", "g", "7", "8"};
        String[] soal2  = {"7", "b", "8", "5", "6", "9", "n", "f", "y", "6", "9"};
        String[] soal3  = {"u", "h", "b", "n", "7", "6", "5", "1", "g", "7", "9"};
    }

    public static int hitungBanyakAngka(String[] data) {
        int jumlah = 0;

        for (String elemen : data) {
            if (elemen.matches("\\d")) {
                jumlah++;
            }
        }
        return jumlah;
    }
}
