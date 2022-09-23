package com.javarush.task.task34.task3403;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.primes(1);
        solution.primes(2);
        solution.primes(3);
        solution.primes(3571);
        solution.primes(132);
        solution.primes(2000);

//        solution.recurse(1);
//        System.out.println();
//        solution.recurse(2);
//        System.out.println();
//        solution.recurse(3);
//        System.out.println();
        //solution.recurse(3571);
        System.out.println();
        solution.recurse(132);
        System.out.println();
        solution.recurse(2000);
    }

    public void recurse(int n) {
        for (int i = 2; i <= n; i++) {
            if (n % i == 0)
                if (isPrime(i)) {
                    System.out.print(i + " ");
                    recurse(n / i);
                    break;
                }
        }
    }

    public void primes(int n) {
        for (int i = 2; i <= n; i++) {
            if (n % i == 0)
                if (isPrime(i)) {
                    System.out.print(i + " ");
                    n /= i; i--;
                }
        }
        System.out.println();
    }

    public boolean isPrime(int n) {
        if (n < 2) return false;
        int k = (int) Math.sqrt(n);
        for (int i = 2; i < k; i++) {
            if ((i != 2 && i % 2 == 0) || (i != 3 && i % 3 == 0)) continue;
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
