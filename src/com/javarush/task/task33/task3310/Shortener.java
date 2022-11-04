package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

public class Shortener {

    private Long lastId = 0L;

    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy strategy) {
        this.storageStrategy = strategy;
    }

    public synchronized Long getId(String value) {
        if (storageStrategy.containsValue(value))
            return storageStrategy.getKey(value);

        storageStrategy.put(++lastId, value);
        return lastId;
    }

    public synchronized String getString(Long id) {
        return storageStrategy.getValue(id);
    }
}
