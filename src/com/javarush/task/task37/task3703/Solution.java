package com.javarush.task.task37.task3703;

import java.util.concurrent.ConcurrentSkipListMap;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        return new ConcurrentSkipListMap().getClass();
    }
}
