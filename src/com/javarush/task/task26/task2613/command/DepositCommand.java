package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Locale;

class DepositCommand implements Command {
    @Override
    public void execute() {
        Locale.setDefault(Locale.ENGLISH);
        String code = ConsoleHelper.askCurrencyCode(); // asks for the currency code
        String[] digits = ConsoleHelper.getValidTwoDigits(code); // asks for the denomination and number of banknotes
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code); // gets a new currency manipulator
        int denomination = Integer.parseInt(digits[0]);
        int count = Integer.parseInt(digits[1]);
        manipulator.addAmount(denomination, count); // adds banknotes to the manipulator
    }
}
