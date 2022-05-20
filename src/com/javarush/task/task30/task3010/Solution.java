package com.javarush.task.task30.task3010;

import java.util.Locale;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String... arguments) {
        System.out.println(getBaseOfNumberSystem("10aAZz"));
    }

    private static int getBaseOfNumberSystem(String argument) {
        if(Pattern.matches("^[\\w&&[^_]]*$", argument)) {
            int max = 0;
            for(char ch : argument.toLowerCase(Locale.ROOT).toCharArray()) {
                max = ch > max ? ch : max;
            }
            System.out.println(max);
            if(max > 47 && max < 58) return max < 50 ? 2 : max - 47;
            return max - 86;
        }
        return -1;
    }
}
