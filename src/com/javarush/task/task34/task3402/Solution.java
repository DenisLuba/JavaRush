package com.javarush.task.task34.task3402;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.factorial(9));
        System.out.println(solution.factorial(0));
        System.out.println(solution.factorial(1));
    }

    public int factorial(int n) {
        if (n > 0) return n * factorial(n - 1);
        return 1;
    }
}
