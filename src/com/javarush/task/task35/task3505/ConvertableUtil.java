package com.javarush.task.task35.task3505;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class ConvertableUtil {
    public static<K, V extends Convertable<K>> Map<K, V> convert(List<V> list) {
        Map<K, V> result = new HashMap<>();
        for(V value : list)
            result.put(value.getKey(), value);
        return result;
    }
}
