package com.javarush.task.task35.task3509;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        return new ArrayList<>(Arrays.asList(elements));
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }

    public static <K, V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) throws IllegalArgumentException {
        if (keys.size() != values.size()) throw new IllegalArgumentException();

        HashMap<K, V> map = new HashMap<>();
        for(int i = 0; i < keys.size(); i++)
            map.put(keys.get(i), values.get(i));
        return map;
    }
}
