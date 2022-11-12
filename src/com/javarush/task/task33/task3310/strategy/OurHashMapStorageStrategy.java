package com.javarush.task.task33.task3310.strategy;

import java.util.Objects;

public class OurHashMapStorageStrategy implements StorageStrategy {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size = 0;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;

    // hash
    private static int hash(Long key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    // indexFor
    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    // getEntry
    public Entry getEntry(Long key) {
        int hash = hash(key);
        int bucketIndex = indexFor(hash, table.length);

        for (Entry entry = table[bucketIndex];
             entry != null;
             entry = entry.getNext()) {

            if (entry.getHash() == hash && Objects.equals(entry.getKey(), key))
                return entry;
        }
        return null;
    }

    // resize
    private void resize(int newCapacity) {
        if (table.length == (1 << 30)) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) (newCapacity * loadFactor);
    }

    // transfer
    private void transfer(Entry[] newTable) {
        for (int i = 0; i < table.length; i++) {
            Entry entry = table[i];

            if (entry != null) {
                table[i] = null;
                do {
                    Entry next = entry.getNext();
                    int bucketIndex = indexFor(entry.getHash(), newTable.length); // место в новой таблице
                    entry.setNext(newTable[bucketIndex]); // теперь у entry новый next (если null, то null,
                    // иначе - тот, что добавили в предудущей итерации)
                    newTable[bucketIndex] = entry;
                    entry = next;
                } while (entry != null);
            }
        }
    }

    // addEntry
    private void addEntry(int hash, Long key, String value, int bucketIndex) {
        for (Entry entry = table[bucketIndex];
             entry != null;
             entry = entry.getNext()) {

            if (entry.getHash() == hash && Objects.equals(entry.getKey(), key)) {
                entry.setValue(value);
                return;
            }
        }
        createEntry(hash, key, value, bucketIndex);
    }

    // createEntry
    private void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, entry);

        if (++size >= threshold) resize(table.length << 1);
    }

    // containsKey
    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    // containsValue
    @Override
    public boolean containsValue(String value) {
        return getKey(value) != null;
    }

    // put
    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int bucketIndex = indexFor(hash, table.length);

        addEntry(hash, key, value, bucketIndex);
    }

    // getKey
    @Override
    public Long getKey(String value) {
        for (Entry bucket : table)
            for (Entry entry = bucket;
                 entry != null;
                 entry = entry.getNext())

                if (entry.getValue().equals(value))
                    return entry.getKey();

        return null;
    }

    // getValue
    @Override
    public String getValue(Long key) {
        Entry entry = getEntry(key);
        return entry != null ? entry.getValue() : null;
    }
}
