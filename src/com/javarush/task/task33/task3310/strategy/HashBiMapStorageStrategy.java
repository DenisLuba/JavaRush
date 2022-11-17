package com.javarush.task.task33.task3310.strategy;

//import com.google.common.collect.HashBiMap;

public class HashBiMapStorageStrategy implements StorageStrategy {
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
//
//    private HashBiMap<Long, String> data;
//
//    public HashBiMapStorageStrategy() {
//        data = HashBiMap.create();
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
//        return data.inverse().get(value);
//    }
//
//    @Override
//    public String getValue(Long key) {
//        return data.get(key);
//    }
}
