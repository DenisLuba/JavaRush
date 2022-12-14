package com.javarush.task.task38.task3804;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Solution {
    public static Class getFactoryClass() {
        return FactoryClass.class;
    }
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println(getFactoryClass()
                .getDeclaredMethods()[0]
                .invoke(getFactoryClass(), null));
    }
}
