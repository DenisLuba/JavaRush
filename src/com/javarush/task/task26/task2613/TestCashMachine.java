package com.javarush.task.task26.task2613;

import org.junit.Assert;
import org.junit.Test;

public class TestCashMachine {
    @Test
    public void testGetManipulatorByCurrencyCode() {
        Assert.assertSame(CurrencyManipulatorFactory.getManipulatorByCurrencyCode("usD").getCurrencyCode(), CurrencyManipulatorFactory.getManipulatorByCurrencyCode("USD").getCurrencyCode());
        Assert.assertSame(CurrencyManipulatorFactory.getManipulatorByCurrencyCode("usD"), CurrencyManipulatorFactory.getManipulatorByCurrencyCode("USD"));
    }
}
