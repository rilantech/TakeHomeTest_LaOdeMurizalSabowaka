package com.take_home_test.laodemurizalsabowaka.jawaban_logical_test;

public class SoalNomor2 {
    public static void main(String[] args) {
        int i = 1;
        do {
            System.out.print(i);

            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println(" FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println(" Fizz");
            } else if (i % 5 == 0) {
                System.out.println(" Buzz");
            } else {
                System.out.println();
            }
            i++;
        } while(i <= 100);
    }
}
