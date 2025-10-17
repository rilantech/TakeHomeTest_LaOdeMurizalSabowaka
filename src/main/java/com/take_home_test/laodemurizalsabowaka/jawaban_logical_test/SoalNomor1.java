package com.take_home_test.laodemurizalsabowaka.jawaban_logical_test;

public class SoalNomor1 {

    public static void main(String[] args) {

        String input = perbaikiKalimat("""
                 italem irad irigayaj
                 iadab itsap ulalreb
                 nalub kusutret gnalali
                """);
        System.out.println(input);
    }

    public static String perbaikiKalimat(String input){
        StringBuilder hasilKonversi = new StringBuilder();

        String[] lines = input.strip().split("\\R");
        for (String line : lines) {
            String[] words = line.trim().split(" ");
            StringBuilder reversedLine = new StringBuilder();

            for (int i = 0; i < words.length; i++) {
                String reversedWord = new StringBuilder(words[i]).reverse().toString();
                reversedLine.append(reversedWord);
                if (i < words.length - 1) reversedLine.append(" ");
            }
            hasilKonversi.append(reversedLine).append("\n");
        }

        return hasilKonversi.toString();
    }
}
