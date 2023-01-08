package com.javarush.task.task39.task3902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;

public class Solution {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter a number: ");
        long l = Long.parseLong(reader.readLine());
//        Instant start = Instant.now();
        String result = isWeightEven(Long.MAX_VALUE) ? "even" : "odd";
//        Instant finish = Instant.now();
        System.out.println("The entered number has " + result + " ones");
//        System.out.println(finish.getNano() - start.getNano());
    }

    public static boolean isWeightEven(long number) {
//        return Long.toBinaryString(number).chars().filter(ch -> ch == '1').count() % 2 == 0;

        int count = 0;
        for (long x = number; x > 0; count++) x &= x - 1;

        return count % 2 == 0;
    }
}
