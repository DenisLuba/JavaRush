package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        // разделил size коллекции на 0.75, метод setScale() : 0 цифр после запятой, округление вверх, привел к int
        int collectionSize = BigDecimal.valueOf(collection.size() / 0.75).setScale(0, RoundingMode.UP).intValue();
        // capacity - вместимость, англ.
        int capacity = Math.max(16, collectionSize);
        map = new HashMap<>(capacity);
        this.addAll(collection);
    }
    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean add(E e) {
        if (map.containsKey(e)) return false;
        map.put(e, PRESENT);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(E e : c)
            map.put(e, PRESENT);

        return true;
    }
}
