package com.javarush.task.task34.task3408;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();  //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        V result = cache.get(key);
        if (result == null) {
            try {
                result = clazz.getConstructor(key.getClass()).newInstance(key);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                e.printStackTrace();
            }
            cache.put(key, result);
        }
        return result;
    }

    public boolean put(V obj) {
        //TODO add your code here
        K key = null;
        int size = cache.size();
        try {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
            key = (K) method.invoke(obj);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            return false;
        }
        cache.put(key, obj);
        return cache.size() > size;
    }

    public int size() {
        return cache.size();
    }
}
