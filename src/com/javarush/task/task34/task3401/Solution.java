package com.javarush.task.task34.task3401;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.fibonacci(9));
        System.out.println(solution.fibonacci(5));
        System.out.println(solution.fibonacci(2));
        System.out.println(solution.fibonacci(1));
    }

    public int fibonacci(int n) {
        if (n == 1) return 1;
        if (n == 0) return 0;
        return fibonacci(n - 2) + fibonacci(n - 1);
    }
}
