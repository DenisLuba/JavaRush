package com.javarush.task.task39.task3904;

import java.util.Arrays;

public class Solution {
    private static int n = 70;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n < 0) return 0;
        long[] table = new long[n + 1];
        Arrays.fill(table, 0);
        table[0] = 1;
        for (int i = 1; i <= n; i++) {
            table[i] += table[i - 1];
            if(i >= 2) table[i] += table[i - 2];
            if(i >= 3) table[i] += table[i - 3];
        }
        return table[n];
    }
}
