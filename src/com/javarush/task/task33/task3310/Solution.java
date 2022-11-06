package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {

    }

    public Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<>();
        for(String value : strings)
            ids.add(shortener.getId(value));

        return ids;
    }

    public Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for(Long id : keys)
            strings.add(shortener.getString(id));

        return strings;
    }

    public void testStrategy(StorageStrategy strategy, long elementsNumber) {

    }
}
