package com.take_home_test.laodemurizalsabowaka.jawaban_logical_test;

public class SoalNomor4 {
    public static void main(String[] args) {
        int[] soal1 = {7, 8, 3, 10, 8};
        int[] soal2 = {5, 12, 11, 12, 10};
        int[] soal3 = {7, 18, 27, 10, 29};
        int[] soal4 = {20, 17, 15, 14, 10};
    }

    public static int getHargaBeliTerbaik(int[] harga) {

        int minHarga = harga[0];
        int maxUntung = 0;
        int keuntunganTerbaik = harga[0];

        for (int i = 1; i < harga.length; i++) {
            int untung = harga[i] - minHarga;

            if (untung > maxUntung) {
                maxUntung = untung;
                keuntunganTerbaik = minHarga;
            }

            if (harga[i] < minHarga) {
                minHarga = harga[i];
            }
        }

        return keuntunganTerbaik;
    }
}
