package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int size = 0;
        for (List<V> list : map.values())
            size += list.size();

        return size;
    }

    @Override
    public V put(K key, V value) {
        if (!map.containsKey(key)) {

            List<V> values = new ArrayList<>();
            values.add(value);
            map.put(key, values);
            return null;

        } else if (map.get(key).size() < repeatCount) {
            map.get(key)                          // получаем List<V> по ключу key
                    .add(value);                  // добавляем в конец List<V> новый элемент

        } else {
            map.get(key)                          // получаем List<V> по ключу key
                    .remove(0);             // удаляем первый элемент из List<V>
            map.get(key)
                    .add(value);                  // добавляем в конец List<V> новый элемент
        }
        return map.get(key)                       // получаем List<V> по ключу key
                .get(map.get(key).size() - 2);    // получаем предпоследний элемент из List<V>
    }

    @Override
    public V remove(Object key) {
        V result = null;
        if (map.containsKey(key)) {
            List<V> list = map.get(key);
            if (list.size() > 1) {
                result = list.remove(0);
            }
            else {
                if (list.size() == 1)
                    result = list.get(0);

                map.remove(key, list);
            }
        }
        return result;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (List<V> list : map.values())
            values.addAll(list);

        return values;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (List<V> list : map.values())
            if (list.contains(value)) return true;

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}
