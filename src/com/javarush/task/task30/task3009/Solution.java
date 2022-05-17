package com.javarush.task.task30.task3009;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));
        System.out.println(getRadix("123"));
        System.out.println(getRadix("5321"));
        System.out.println(getRadix("1A"));
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> radix = new HashSet<>();

        for(int i = 2; i <= 36; i++) {
            try {
                String num = convertNumberToOtherNumberSystem(number, i);
                if (isPalindrom(num)) radix.add(i);
            } catch (NumberFormatException e) {
                return radix;
            }
        }

        return radix;
    }

    private static String convertNumberToOtherNumberSystem(String number, int numberSystem) throws NumberFormatException {
        BigDecimal result = new BigDecimal(number);
        StringBuilder sb = new StringBuilder();
        BigDecimal d1;
        BigDecimal d2 = new BigDecimal(numberSystem);

        while(!result.equals(BigDecimal.ZERO)) {
            d1 = result.divide(d2, RoundingMode.FLOOR);
            int num = (result.subtract(d1.multiply(d2))).intValue();
            result = d1;
            char res = num < 10 ? (char) (num + 48) : (char) (num + 87);
            sb.append(res);
        }

        return sb.reverse().toString();
    }

    private static boolean isPalindrom(String number) {
        int i = 0, j = number.length() - 1;
        for(; i < j; i++, j--)
            if (number.charAt(i) != number.charAt(j)) return false;

        return true;
    }
}
