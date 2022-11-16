package com.javarush.task.task37.task3701;

import java.util.*;

public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
                for (int i = 4; i < 10_000; i++)
            list.add(i);

        System.out.println();

        new Thread(() -> {
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignore) { }
                System.out.println("Thread: " + iterator.next());
                iterator.remove();
            }
        }).start();

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException ignore) { }
            System.out.println("main " + iterator.next());
            iterator.remove();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator(this);
    }

    public class RoundIterator implements Iterator<T> {

        Solution<T> solution;
        int size;
        int nextValue, currentValue;

        public RoundIterator(Solution<T> solution) {
            this.solution = solution;
            size = solution.size();
            nextValue = 0;
            currentValue = 0;
        }

        @Override
        public boolean hasNext() throws ConcurrentModificationException {
            if (isConcurrentModification()) throw  new ConcurrentModificationException();

            return nextValue < size;
        }

        @Override
        public T next() {
            if (isConcurrentModification()) throw new ConcurrentModificationException();

            T t = solution.get(currentValue = nextValue);
            if (++nextValue == size) nextValue = 0;
            return t;
        }

        @Override
        public void remove() throws ConcurrentModificationException {
            if (isConcurrentModification()) throw new ConcurrentModificationException();

            solution.remove(currentValue);
            nextValue = nextValue - 1 < 0 ? 0 : --nextValue;
            size--;
        }

        private boolean isConcurrentModification() {
            return size != solution.size();
        }
    }
}
