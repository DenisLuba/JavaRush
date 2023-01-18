package com.javarush.task.task26.task2613;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class TestCashMachine {
    @Test
    public void testGetManipulatorByCurrencyCode() {
        Assert.assertSame(CurrencyManipulatorFactory.getManipulatorByCurrencyCode("usD").getCurrencyCode(), CurrencyManipulatorFactory.getManipulatorByCurrencyCode("USD").getCurrencyCode());
        Assert.assertSame(CurrencyManipulatorFactory.getManipulatorByCurrencyCode("usD"), CurrencyManipulatorFactory.getManipulatorByCurrencyCode("USD"));
    }

    @Test
    public void testAddAmount() {
        Locale.setDefault(Locale.ENGLISH);
        String code = ConsoleHelper.askCurrencyCode();
        String[] digits = ConsoleHelper.getValidTwoDigits(code);
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        int denomination = Integer.parseInt(digits[0]);
        int count = Integer.parseInt(digits[1]);
        manipulator.addAmount(denomination, count);
        CurrencyManipulator m = CurrencyManipulatorFactory.getManipulatorByCurrencyCode("Usd");
        Assert.assertSame(manipulator, m);
    }
}
