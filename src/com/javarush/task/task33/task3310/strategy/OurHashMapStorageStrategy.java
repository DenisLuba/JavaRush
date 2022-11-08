package com.javarush.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    int size;
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    float loadFactor = DEFAULT_LOAD_FACTOR;

    public static int hash(Long k) {
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    public int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    public Entry getEntry(Long key) {
        int hash = (key == null) ? 0 : hash(key);
        for (Entry entry = table[indexFor(hash, table.length)];
             entry != null;
             entry = entry.next) {

            Object k;
            if (entry.hash == hash &&
                    ((k = entry.key) == key || (key != null && key.equals(k))))
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

    public void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, entry);
        if (size++ >= threshold)
            resize(2 * table.length);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, entry);
        size++;
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
        int index = indexFor(hash, table.length);
        for (Entry entry = table[indexFor(hash, table.length)];
        entry != null;
        entry = entry.next) {
            Object k;
            if (entry.hash == hash && ((k = entry.key) == key || key.equals(k)))
                return;
        }
        addEntry(hash, key, value, index);
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
