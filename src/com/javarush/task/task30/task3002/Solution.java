package com.javarush.task.task30.task3002;

public class Solution {
    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012")); //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62")); //62
    }

    public static String convertToDecimalSystem(String s) {
        int numberSystem = 10;
        if(s.charAt(0) == '0') {
            switch (s.charAt(1)) {
                case 'b': numberSystem = 2;
                    s = s.substring(2);
                    break;
                case 'x': numberSystem = 16;
                    s = s.substring(2);
                    break;
                default: numberSystem = 8;
                    s = s.substring(1);
            }
        }

        int result = Integer.parseInt(s, numberSystem);
        return Integer.toString(result);
    }
}
