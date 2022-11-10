package com.javarush.task.task33.task3310.strategy;

import java.util.Objects;

public class OurHashMapStorageStrategy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    int size = 0;
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    float loadFactor = DEFAULT_LOAD_FACTOR;

    public static int hash(Long k) {
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    public int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    // getEntry
    public Entry getEntry(Long key) {
        int hash = hash(key);
        int bucketIndex = indexFor(hash, table.length);

        for (Entry entry = table[bucketIndex];
             entry != null;
             entry = entry.next) {

            if (entry.hash == hash && Objects.equals(entry.key, key))
                return entry;
        }
        return null;
    }

    public void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == (1 << 30)) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) (newCapacity * loadFactor);
    }

    public void transfer(Entry[] newTable) {
        Entry[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entry entry = src[j];
            if (entry != null) {
                src[j] = null;
                do {
                    Entry next = entry.next;
                    int i = indexFor(entry.hash, newCapacity);
                    entry.next = newTable[i];
                    newTable[i] = entry;
                    entry = next;
                } while (entry != null);
            }
        }
    }


    // addEntry

    private void addEntry(int hash, Long key, String value, int bucketIndex) {
        for (Entry entry = table[bucketIndex]; entry != null;
             entry = entry.next) {

            if (entry.hash == hash && Objects.equals(entry.key, key)) {
                entry.value = value;
                return;
            }
        }
        createEntry(hash, key, value, bucketIndex);
    }

    // createEntry
    public void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, entry);

        if (++size >= threshold) resize(table.length * 2);
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        Entry[] tab = table;
        for (int i = 0; i < tab.length; i++)
            for (Entry entry = tab[i]; entry != null; entry = entry.next)
                if (value.equals(entry.value))
                    return true;
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int bucketIndex = indexFor(hash, table.length);

        addEntry(hash, key, value, bucketIndex);
    }

    @Override
    public Long getKey(String value) {
        for (int i = 0; i < table.length; i++)
            if (table[i].getValue().equals(value))
                return table[i].getKey();

        return null;
    }

    @Override
    public String getValue(Long key) {
        for (int i = 0; i < table.length; i++)
            if (table[i].getKey() == key)
                return table[i].getValue();

        return null;
    }
}
