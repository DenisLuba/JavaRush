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

        public boolean hasLeftChildren() { return !availableToAddLeftChildren; }

        public boolean hasRightChildren() { return !availableToAddRightChildren; }

        public boolean hasChildren() { return hasRightChildren() || hasLeftChildren(); }

        public boolean removeEntry() {
            parent = null;
            leftChild = null;
            rightChild = null;
            elementName = null;
            numberOfElement = 0;
            availableToAddLeftChildren = false;
            availableToAddRightChildren = false;
            return true;
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
        if (false) { // если дерево пустое,
            root = entry; // кладем новый элемент в корень
            size++; // размер теперь равен 1
        } else {
            queue.add(root); // в начало очереди кладем первый элемент
            Entry parentEntry = root;
            Entry tempEntry = parentEntry;
            while (!queue.isEmpty()) { // пока очередь не пустая
                parentEntry = queue.poll(); // забираем первый элемент из очереди
                if (!parentEntry.hasChildren()) // если у элемента нет детей, то выходим из цикла
                    break;
                // пока у первого элемента в очереди есть потомки
                if (parentEntry.hasLeftChildren())
                    queue.add(parentEntry.leftChild); // добавляем в конец очереди потомков извлеченного элемента
                if (parentEntry.hasRightChildren())
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
            size++; // увеличиваем размер
        }
        return true;
    }

    public boolean remove(Object o) {
        if (!(o instanceof String)) throw new UnsupportedOperationException();
        String element = (String) o;
        Entry<String> removeElement = getEntry(element);
        long count = 0;
        Queue<Entry> queue = new LinkedList<>();
        Entry entry;
        queue.add(removeElement);
        while (!queue.isEmpty()) {
            entry = queue.poll();
            count++;
            if (entry.hasLeftChildren()) queue.add(entry.leftChild);
            if (entry.hasRightChildren()) queue.add(entry.rightChild);
        }
        size -= count;

        Entry<String> parent = removeElement.parent;
        if (parent != null) {
            if (parent.leftChild.equals(removeElement)) {
                parent.leftChild = null;
                parent.availableToAddLeftChildren = true;
            } else {
                parent.rightChild = null;
                parent.availableToAddRightChildren = true;
            }
        }
        return true;
    }

    public Entry getEntry(int index) {
        Queue<Entry> queue = new LinkedList();
        Entry entry;
        queue.add(root);
        while (!queue.isEmpty()) {
            entry = queue.poll();
            if (entry.numberOfElement == index) return entry;
            if (entry.hasLeftChildren()) queue.add(entry.leftChild);
            if (entry.hasRightChildren()) queue.add(entry.rightChild);
        }
        return null;
    }

    public Entry getEntry(String elementName) {
        Queue<Entry> queue = new LinkedList<>();
        Entry entry;
        queue.add(root);
        while (!queue.isEmpty()) {
            entry = queue.poll();
            if (entry.elementName.equals(elementName)) return entry;
            if (entry.hasLeftChildren()) queue.add(entry.leftChild);
            if (entry.hasRightChildren()) queue.add(entry.rightChild);
        }
        return null;
    }

    public void walkToTree() {
        if (size == 0) return;
        Queue<Entry> queue = new LinkedList<>();
        Entry entry;
        queue.add(root);
        while (!queue.isEmpty()) {
            entry = queue.poll();
            System.out.printf("Name of element: %s, number of element: %d%n", entry.elementName, entry.numberOfElement);
            if (entry.hasLeftChildren()) queue.add(entry.leftChild);
            if (entry.hasRightChildren()) queue.add(entry.rightChild);
        }
    }

    public String getParent(String s) {
        Entry<String> entry = getEntry(s);
        return entry == null ? null : entry.parent.elementName;
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
