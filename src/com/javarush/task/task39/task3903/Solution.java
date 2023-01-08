package com.javarush.task.task39.task3903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter a number: ");
        long number = Long.parseLong(reader.readLine());
        System.out.println("Please enter the first index: ");
        int i = Integer.parseInt(reader.readLine());
        System.out.println("Please enter the second index: ");
        int j = Integer.parseInt(reader.readLine());
        reader.close();
        
        System.out.println("The result of swapping bits is " + swapBits(number, i, j));
    }

    public static long swapBits(long n, int i, int j) {
        if (((n >>> i) & 1) == ((n >>> j) & 1L)) return n; // проверяем, не равны ли друг другу биты на позициях i и j
        long bitMask = (1L << i) | (1L << j); // создаем маску из единиц на позициях i и j
        return n ^ bitMask; // XOR с маской из 1 на нужных позициях меняет 1 на 0 и 0 на 1
        // т.к. мы знаем, что на позициях i и j разные биты (в одном месте 1, в другом 0)

//        StringBuilder f = new StringBuilder();
//        for (int k = 0; k < 64; k++) f.append("0");
////        String f = "0".repeat(64);
//        String number = Long.toBinaryString(n);
//        f.replace(64 - number.length(), 64, number).reverse();
//        number = String.valueOf(f);
//        f.setCharAt(i, number.charAt(j));
//        f.setCharAt(j, number.charAt(i));
//        return Long.parseLong(String.valueOf(f.reverse()), 2);
    }
}
