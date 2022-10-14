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
        Set<E> keys = map.keySet();
        return keys.iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        if (!map.containsKey(o)) return false;
        map.remove(o);
        return true;
    }

    @Override
    public boolean add(E e) {
        if (map.containsKey(e)) return false;
        map.put(e, PRESENT);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = true;
        for(E e : c)
            if (!map.containsKey(e)) map.put(e, PRESENT);
            else result = false;

        return result;
    }

    @Override
    public Object clone() throws InternalError {
        AmigoSet<E> amigoSet = new AmigoSet<>();
        try{
            amigoSet.map = (HashMap<E, Object>) this.map.clone();
        } catch (Exception e) {
            throw new InternalError();
        }
        return amigoSet;
    }
}
