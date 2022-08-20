package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    static class Entry<T> implements Serializable {
        String elementName;
        long numberOfElement;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }

    Entry<String> root;
    private int size;

    public CustomTree() {
        this.root = new Entry<>("root");
    }

    @Override
    public boolean add(String s) {
        Entry<String> entry = new Entry<>(s);
        entry.numberOfElement = size + 1;
        Queue<Entry> queue = new LinkedList<>();
        if (size == 0) { // если дерево пустое,
            root = entry; // кладем новый элемент в корень
            size++; // размер теперь равен 1
        } else {
            queue.add(root); // в начало очереди кладем первый элемент
            Entry parentEntry;
            Entry tempEntry = root;
            while ((Objects.requireNonNull(parentEntry = queue.poll())).isAvailableToAddChildren()) { // забираем первый элемент из очереди
                // пока у первого элемента в очереди есть потомки
                queue.add(parentEntry.leftChild); // добавляем в конец очереди потомков извлеченного элемента
                queue.add(parentEntry.rightChild); // сначала левого, за ним - правого
                tempEntry = parentEntry;
            }
            if (tempEntry.availableToAddLeftChildren) {
                tempEntry.leftChild = entry; // если у предыдущего элемента нет левого child, добавляем
                tempEntry.availableToAddLeftChildren = false;
                entry.parent = tempEntry;
            }
            else if (tempEntry.availableToAddRightChildren) {
                tempEntry.rightChild = entry; // иначе, если нет правого, добавляем
                tempEntry.availableToAddRightChildren = false;
                entry.parent = tempEntry;
            }
            else {
                parentEntry.leftChild = entry; // иначе добавляем child первому бездетному элементу
                parentEntry.availableToAddLeftChildren = false;
                entry.parent = parentEntry;
            }
            size++;
        }
        return true;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }
}
