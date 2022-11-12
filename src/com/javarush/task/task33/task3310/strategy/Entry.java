package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;
import java.util.Objects;

public class Entry implements Serializable {
    private final int hash;
    private final Long key;
    private String value;
    private Entry next;

    public Entry(int hash, Long key, String value, Entry next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Long getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Entry getNext() {
        return next;
    }

    public int getHash() {
        return hash;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setNext(Entry next) {
        this.next = next;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return key.equals(entry.key) && value.equals(entry.value);
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
