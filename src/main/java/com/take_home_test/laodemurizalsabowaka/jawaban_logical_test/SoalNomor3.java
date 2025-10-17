package com.take_home_test.laodemurizalsabowaka.jawaban_logical_test;

public class SoalNomor3 {
    public static void main(String[] args) {
        int jumlahDeret = 9;
        int angkaPertama = 0;
        int angkaKedua = 1;

        System.out.print(angkaPertama + ", " + angkaKedua);

        int i = 2;
        do{
            int selanjutnya = angkaPertama + angkaKedua;
            System.out.print(", " + selanjutnya);
            angkaPertama = angkaKedua;
            angkaKedua = selanjutnya;
            i++;
        }while(i < jumlahDeret);
    }
}
