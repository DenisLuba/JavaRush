package com.javarush.task.task39.task3908;

import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("caSaC"));
    }

    public static boolean isPalindromePermutation(String s) {

        return s != null && !(s.length() == 0)   // if (s == null || s.length() == 0) return false
                && s.chars()  // IntStream
                .mapToObj(Character::toLowerCase)  // Stream<Integer>
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))  // Map<Integer, Long>
                .values()  // Collection<Long>
                .stream() // Stream<Long>
                .filter(i -> i % 2 != 0) // odd numbers only
                .count() <= 1; // there should be only one left
    }
}
