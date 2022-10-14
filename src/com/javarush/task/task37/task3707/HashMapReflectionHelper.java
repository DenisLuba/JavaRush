package com.javarush.task.task37.task3707;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class HashMapReflectionHelper {
    public static <T> T callHiddenMethod(HashMap map, String methodName) {
        try {
            Method method = map.getClass().getDeclaredMethod(methodName);
            method.setAccessible(true);
            return (T) method.invoke(map);
        }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
        }
        return null;
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("key_1", "value_1");
        map.put("key_2", "value_2");
        map.put("key_3", "value_3");
        map.put("key_4", "value_4");
        map.put("key_5", "value_5");
        map.put("key_6", "value_6");

        int capacity = (Integer) callHiddenMethod(map, "capacity");
    }
}
