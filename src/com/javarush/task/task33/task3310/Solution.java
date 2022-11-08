package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {

        testStrategy(new HashMapStorageStrategy(), 10_000);
        testStrategy(new OurHashMapStorageStrategy(), 10_000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<>();
        for(String value : strings)
            ids.add(shortener.getId(value));

        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for(Long id : keys)
            strings.add(shortener.getString(id));

        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        // 1
        Helper.printMessage(strategy.getClass().getSimpleName());

        // 2
        Set<String> values = new HashSet<>();
        for (long key = 0; key < elementsNumber; key++) {
            String randomString = Helper.generateRandomString();
            strategy.put(key, randomString);
            values.add(randomString);
        }

        // 3
        Shortener shortener = new Shortener(strategy);

        // 4
        Set<Long> ids;
        long startForIds = new Date().getTime();

        ids = getIds(shortener, values);

        Long resultForIds = new Date().getTime() - startForIds;
        Helper.printMessage(String.valueOf(resultForIds));

        // 5
        Set<String> strings;
        long startForStrings = new Date().getTime();

        strings = getStrings(shortener, ids);

        Long resultForStrings = new Date().getTime() - startForStrings;
        Helper.printMessage(String.valueOf(resultForStrings));

        // 6
        if (values.equals(strings))
            Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");
    }
}
