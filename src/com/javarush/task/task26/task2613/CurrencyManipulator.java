package com.javarush.task.task26.task2613;

import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {
    private final String currencyCode;
    private final Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new TreeMap<>();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        denominations.put(denomination,
                denominations.get(denomination) != null ?
                        denominations.get(denomination) + count :
                        count);
    }

    public int getTotalAmount() {
        return denominations.entrySet().stream().mapToInt(pair -> pair.getKey() * pair.getValue()).sum();
    }

    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }
}
