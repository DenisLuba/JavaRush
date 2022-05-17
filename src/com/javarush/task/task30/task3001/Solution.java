package com.javarush.task.task30.task3001;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Solution {
    public static void main(String[] args) throws NumberFormatException {
//        Number number = new Number(NumberSystemType._10, "6");
//        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
//        System.out.println(result); //expected 110
//
//        number = new Number(NumberSystemType._16, "6df");
//        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
//        System.out.println(result); //expected 3337

        Number number = new Number(NumberSystemType._16, "abcdefabcdef");
        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result); //expected abcdefabcdef
    }

    public static Number convertNumberToOtherNumberSystem(Number number, NumberSystem expectedNumberSystem) throws NumberFormatException {
        int newNumberSystem = expectedNumberSystem.getNumberSystemIntValue();
        int oldNumberSystem = number.getNumberSystem().getNumberSystemIntValue();
        String digit = number.getDigit();
        int j = 0, i = digit.length() - 1;
        BigDecimal result = new BigDecimal("0");
        for (; i >= 0; i--, j++) {
            char ch = digit.charAt(i);
            int num = getInteger(ch, oldNumberSystem);
            result = result.add(new BigDecimal((int) (Math.pow(oldNumberSystem, j) * num)));
        }

        System.out.println(result);

        StringBuilder sb = new StringBuilder();
        BigDecimal d1;
        BigDecimal d2;

        while(!result.equals(BigDecimal.ZERO)) {
            d2 = new BigDecimal(newNumberSystem);
            d1 = result.divide(d2, RoundingMode.FLOOR);

            int num = (result.subtract(d1.multiply(d2))).intValue();
            result = d1;
            if(num > 9) {
                char res = setInteger(num);
                sb.append(res);
            } else sb.append(num);
        }

        String newDigit = sb.reverse().toString();
        return new Number(expectedNumberSystem, newDigit);
    }

    private static int getInteger(char ch, int numberSystem) throws NumberFormatException {
        int lastChar = numberSystem + 87;
        if (ch > 47 && ch < 58) {
            int i = ch - 48;
            if (i < numberSystem) return i;
            throw new NumberFormatException();
        } else if (ch > 96 && ch < lastChar && ch < 123) {
            return ch - 87;
        } else throw new NumberFormatException();
    }

    private static char setInteger(int num) {
        switch (num) {
            case 10 : return 'a';
            case 11 : return 'b';
            case 12 : return 'c';
            case 13 : return 'd';
            case 14 : return 'e';
            case 15 : return 'f';
            default : return 0;
        }
    }
}
