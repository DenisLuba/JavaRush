package com.javarush.task.task26.task2613;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        String code = ConsoleHelper.askCurrencyCode();
        String[] digits = ConsoleHelper.getValidTwoDigits(code);
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        int denomination = Integer.parseInt(digits[0]);
        int count = Integer.parseInt(digits[1]);
        manipulator.addAmount(denomination, count);

        System.out.println(manipulator.equals(CurrencyManipulatorFactory.getManipulatorByCurrencyCode("usD")));
    }
}
