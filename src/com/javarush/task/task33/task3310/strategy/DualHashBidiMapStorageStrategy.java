package com.javarush.task.task33.task3310.strategy;

//import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public class DualHashBidiMapStorageStrategy implements StorageStrategy {
    @Override
    public boolean containsKey(Long key) {
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        return false;
    }

    @Override
    public void put(Long key, String value) {

    }

    @Override
    public Long getKey(String value) {
        return null;
    }

    @Override
    public String getValue(Long key) {
        return null;
    }

//    private DualHashBidiMap<Long, String> data;
//
//    public DualHashBidiMapStorageStrategy() {
//        data = new DualHashBidiMap<>();
//    }
//
//    @Override
//    public boolean containsKey(Long key) {
//        return data.containsKey(key);
//    }
//
//    @Override
//    public boolean containsValue(String value) {
//        return data.containsValue(value);
//    }
//
//    @Override
//    public void put(Long key, String value) {
//        data.put(key, value);
//    }
//
//    @Override
//    public Long getKey(String value) {
//        return data.getKey(value);
//    }
//
//    @Override
//    public String getValue(Long key) {
//        return data.get(key);
//    }
}
