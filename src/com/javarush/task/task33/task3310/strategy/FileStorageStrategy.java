package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;

    private FileBucket[] table;
    private int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private long maxBucketSize;

    public FileStorageStrategy() {
        init();
    }

    private void init() {
        table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < table.length; i++) {
            table[i] = new FileBucket();
        }
    }

    private static int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    public final Entry getEntry(Long key) {
        if (size == 0) {
            return null;
        }

        int index = indexFor(key.hashCode(), table.length);
        for (Entry entry = table[index].getEntry(); entry != null; entry = entry.getNext()) {
            if (key.equals(entry.getKey())) {
                return entry;
            }
        }
        return null;
    }

    private void resize(int newCapacity) {
        FileBucket[] newTable = new FileBucket[newCapacity];

        for (int i = 0; i < newTable.length; i++)
            newTable[i] = new FileBucket();

        transfer(newTable);

        for (int i = 0; i < table.length; i++)
            table[i].remove();

        table = newTable;
    }

    private void transfer(FileBucket[] newTable) {
        int newCapacity = newTable.length;
        maxBucketSize = 0;

        for (FileBucket fileBucket : table) {
            Entry entry = fileBucket.getEntry();
            while (entry != null) {
                Entry next = entry.getNext();
                int indexInNewTable = indexFor(entry.getKey().hashCode(), newCapacity);
                entry.setNext(newTable[indexInNewTable].getEntry());
                newTable[indexInNewTable].putEntry(entry);
                entry = next;
            }

            long currentBucketSize = fileBucket.getFileSize();
            if (currentBucketSize > maxBucketSize)
                maxBucketSize = currentBucketSize;
        }
    }

    private void addEntry(int hash, Long key, String value, int bucketIndex) {
        if ((maxBucketSize > bucketSizeLimit)) {
            resize(2 * table.length);
            bucketIndex = indexFor(key.hashCode(), table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }

    private void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;

        long currentBucketSize = table[bucketIndex].getFileSize();
        if (currentBucketSize > maxBucketSize)
            maxBucketSize = currentBucketSize;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }
    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public Long getKey(String value) {
        for (FileBucket tableElement : table)
            for (Entry entry = tableElement.getEntry(); entry != null; entry = entry.getNext())
                if (value.equals(entry.getValue()))
                    return entry.getKey();
        return null;
    }

    @Override
    public boolean containsValue(String value) {
        for (FileBucket tableElement : table)
            for (Entry entry = tableElement.getEntry(); entry != null; entry = entry.getNext())
                if (value.equals(entry.getValue()))
                    return true;
        return false;
    }

    @Override
    public String getValue(Long key) {
        Entry entry = getEntry(key);
        if (entry != null)
            return entry.getValue();

        return null;
    }

    @Override
    public void put(Long key, String value) {
        int hash = key.hashCode();
        int index = indexFor(hash, table.length);
        for (Entry entry = table[index].getEntry(); entry != null; entry = entry.getNext()) {
            if (key.equals(entry.getKey())) {
                entry.setValue(value);
                return;
            }
        }
        addEntry(hash, key, value, index);
    }
}
