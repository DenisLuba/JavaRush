package com.javarush.task.task30.task3010;

import java.util.Locale;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String... arguments) {
        String result = "incorrect";
        try{
            result = getBaseOfNumberSystem(arguments[0]);
        } catch(Exception e) {}
        System.out.println(result);
    }

    private static String getBaseOfNumberSystem(String argument) {
        // regex включает все буквенно-цифровые символы (\w) и исключает нижнее подчеркивание ([^_])
        // с начала (^) и до конца строки ($) подряд (*)
        if(Pattern.matches("^[\\w&&[^_]]*$", argument)) { // Pattern.matches() возвращает true,
            // если regex соответствует строке argument
            int max = 0;
            for(char ch : argument.toLowerCase(Locale.ROOT).toCharArray()) { // пробежимся по всем символам в строке,
                // уменьшив их, чтобы не обращать внимание на регистр
                max = ch > max ? ch : max; // запишем в переменную max наибольшее значение символа
            }
            if(max > 47 && max < 58) return max < 50 ? "2" : String.valueOf(max - 47); // если символ относится к цифрам, то так...
            return String.valueOf(max - 86); // а если к буквам, то так...
        }
        return "incorrect"; // в случае, когда символы в строке не соответствуют нашему regex, выдадим в ответе -1
    }
}
