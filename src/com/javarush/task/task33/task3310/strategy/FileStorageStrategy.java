package com.javarush.task.task33.task3310.strategy;

import java.util.Objects;

public class FileStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10_000;

    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size = 0;
    long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize;

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    // hash
    public static int hash(Long key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    // indexFor
    public int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    // getEntry
    public Entry getEntry(Long key) {
        int hash = hash(key);
        int bucketIndex = indexFor(hash, table.length);

        FileBucket bucket = table[bucketIndex];

        for (Entry entry = bucket.getEntry();
             entry != null;
             entry = entry.next) {

            if (entry.hash == hash && Objects.equals(entry.key, key))
                return entry;
        }
        return null;
    }

    // resize
    public void resize(int newCapacity) {
        if (table.length == (1 << 30)) {
            bucketSizeLimit = Integer.MAX_VALUE;
            return;
        }

        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
        bucketSizeLimit = bucketSizeLimit << 1; // bucketSizeLimit * 2
    }

    // transfer
    public void transfer(FileBucket[] newTable) {
        for (int i = 0; i < table.length; i++) {
            FileBucket bucket = table[i];
            Entry entry = bucket.getEntry();

            if (entry != null) {
                table[i].remove();
                do {
                    Entry next = entry.next; // создаем ссылку на следующую entry, привязанную к данной
                    int bucketIndex = indexFor(entry.hash, newTable.length); // место в новой таблице
                    entry.next = newTable[bucketIndex].getEntry(); // вытаскиваем из файла entries
                    newTable[bucketIndex].remove(); // удаляем файл, чтобы потом записать новый
                    newTable[bucketIndex] = new FileBucket(); // создаем новый пустой файл
                    newTable[bucketIndex].putEntry(entry); // записываем в него новый файл с добавленной entry
                    entry = next; // переходим к следующей entry, которая уже отвязана от предыдущей entry, которую уже упаковали
                } while (entry != null);
            }
        }
    }

    // addEntry
    private void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = table[bucketIndex].getEntry();

        for ( ; entry != null;
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
        long fileSize = table[bucketIndex].getFileSize();
        Entry entry = null;
        if (fileSize > 0) {
            entry = table[bucketIndex].getEntry();
            table[bucketIndex].remove();
        }

        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, entry));

        size += (table[bucketIndex].getFileSize() - fileSize); // добавили новый размер, вычли старый

        if (size >= bucketSizeLimit) resize(table.length << 1);
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
        for (FileBucket bucket : table)
            for (Entry entry = bucket.getEntry();
                 entry != null;
                 entry = entry.next)

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
