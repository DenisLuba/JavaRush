package com.javarush.task.task26.task2613;

import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {
    private final String currencyCode;
    private Map<Integer, Integer> denominations;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new TreeMap<>();
    }

    public void addAmount(int denomination, int count) {
        denominations.put(denomination,
                denominations.get(denomination) != null ?
                        denominations.get(denomination) + count :
                        count);
    }
}
