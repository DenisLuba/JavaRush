package com.javarush.task.task39.task3909;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isOneEditAway(String first, String second) {
        if (first == null || second == null) return false;
        if (Math.abs(first.length() - second.length()) > 1) return false;
        if (first.length() == 0 || second.length() == 0) return true;
        if (first.length() > second.length()) return differentLengths(second, first);
        if (second.length() > first.length()) return differentLengths(first, second);
        return equalsLength(first, second);
    }

    private static boolean differentLengths(String first, String second) {
        int i = 0, j = 0;
        for (; i < first.length() && j < second.length(); i++, j++)
            if (first.charAt(i) != second.charAt(j)) i--;

        return j - i <= 1;
    }

    private static boolean equalsLength(String first, String second) {
        int j = 0;
        for (int i = 0; i < first.length(); i++)
            if (first.charAt(i) != second.charAt(i)) j++;

        return j <= 1;
    }
    @Test
    public void testMethod() {
        Assert.assertFalse(isOneEditAway("abcde", "abcdrr"));
        Assert.assertFalse(isOneEditAway("abcd", "ABCD"));
        Assert.assertFalse(isOneEditAway("abcde", "abCDe"));
        Assert.assertFalse(isOneEditAway("abcde", "abcdede"));
        Assert.assertFalse(isOneEditAway("", "AbCDE"));
        Assert.assertFalse(isOneEditAway(null, "AbCDE"));

        Assert.assertTrue(isOneEditAway("ABCDE", "ACDE"));
        Assert.assertTrue(isOneEditAway("ABCDE", "AbCDE"));
        Assert.assertTrue(isOneEditAway("ACDE", "AbCDE"));
        Assert.assertTrue(isOneEditAway("ACDE", "AbCDE"));
        Assert.assertTrue(isOneEditAway("ABCDE", "BCDE"));
        Assert.assertTrue(isOneEditAway("ABCDE", "ABCD"));
        Assert.assertTrue(isOneEditAway("", ""));
        Assert.assertTrue(isOneEditAway("", "E"));
    }
}
