package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CurrencyManipulator {
    private final String currencyCode;

    // the map contains the denomination of the currency and the number of banknotes
    private Map<Integer, Integer> denominations;

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

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        map.putAll(denominations);
        Map<Integer, Integer> result = getResultForWithdraw(map, expectedAmount);
        if (result
                .entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey() * entry.getValue())
                .sum() != expectedAmount)
            throw new NotEnoughMoneyException();

        return result;
    }

    private Map<Integer, Integer> getResultForWithdraw(Map<Integer, Integer> map, int expectedAmount) {
        Map<Integer, Integer> result = new TreeMap<>(Comparator.reverseOrder());
        AtomicInteger finalExpectedAmount = new AtomicInteger(expectedAmount);
        denominations = map.entrySet().stream().map(entry -> {
                    int currency = entry.getKey();
                    int numberOfBanknotes = entry.getValue();
                    int division = finalExpectedAmount.get() / currency;

                    if (division == 0) return entry;
                    if (division >= numberOfBanknotes) {
                        result.put(currency, numberOfBanknotes);
                        finalExpectedAmount.addAndGet(-(numberOfBanknotes * currency));
                        entry.setValue(0);
                    } else {
                        result.put(currency, division);
                        finalExpectedAmount.addAndGet(-(division * currency));
                        entry.setValue(numberOfBanknotes - division);
                    }
                    return entry;
                }).filter(entry -> entry.getValue() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return result;
    }
}
