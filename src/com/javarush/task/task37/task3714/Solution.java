package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        String input = s.toUpperCase(); // приводим все символы в верхний регистр
        int result = 0;

        // получаем список римских цифр из enum от большей к меньшей
        List<RomanNumber> romanNumbers = RomanNumber.getReverseSortedValues();

        int i = 0;

        while((input.length() > 0) && (i < romanNumbers.size())) {
            RomanNumber symbol = romanNumbers.get(i);
// если оставшаяся на данном этапе итерации (от введенного искомого римского числа) строка
// начинается с римской цифры из нашего списка, например: "IV"
            if (input.startsWith(symbol.name())) {
                result += symbol.getValue(); // добавляем к нашему результату значение этой римской цифры,
                // например: III = 1 + 1 + 1 - три итерации.
                input = input.substring(symbol.name().length()); // отрезаем от начала строки оставшегося римского числа
                // найденную цифру
                // К следующей римской цифре из List<RomanNumber> пока не переходим
            } else i++; // если такой римской цифры нет в введенном числе,
            // переходим к следующей, меньшей по значению,
            // например: в числе 45 сначала идет XL = 40 (а не IV, как можно ошибиться),
            // потом V = 5
        }

        if (input.length() > 0) { // если остались какие-то символы, значит есть косяк во введенном римском числе
            throw new IllegalArgumentException(input + " cannot be converted" +
                    "to a Roman Numeral.");
        }

        return result; // Возвращаем сумму всех римских цифр от большего разряда к меньшему
    }

    private enum RomanNumber {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private final int value;

        RomanNumber(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        // Получим перевернутый список римских цифр - от большей к меньшей
        // При проверке будем перебирать эти цифры от большей к меньшей,
        // т.к. римские числа тоже записываются от большего разряда к меньшему, например: 94 = XCIV ((XC = 90) + (IV = 4))
        public static List<RomanNumber> getReverseSortedValues() {
            return Arrays.stream(values()) // возвращает stream с значениями enum RomanNumber
                    .sorted(Comparator.comparing((RomanNumber e) -> e.value) // возвращает Comparator по значению - value
                    .reversed()) // возвращает Comparator с обратным порядком
                    .collect(Collectors.toList()); // возвращает List<RomanNumber>
        }
    }
}
