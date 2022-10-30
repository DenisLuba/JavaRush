package com.javarush.task.task36.task3610;

import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new MyMultiMap<>(3);
        for (int i = 0; i < 7; i++) {
            map.put(i, i);
        }
        map.put(5, 56);
        map.put(5, 57);
        System.out.println(map.put(5, 58));

        System.out.println(map);
        System.out.println(map.size());

        System.out.println(map.remove(5));
        System.out.println(map);
        System.out.println(map.size());

        System.out.println(map.keySet());
        System.out.println(map.values());

        System.out.println(map.containsKey(5));
        System.out.println(map.containsValue(57));
        System.out.println(map.containsValue(7));
    }
}
