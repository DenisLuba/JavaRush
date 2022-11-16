package com.javarush.task.task37.task3701;

import java.util.*;
import java.util.function.Consumer;

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
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator<T>(this);
    }

    public class RoundIterator<T> implements Iterator<T> {

        Solution<T> solution;
        int size;
        int count;

        public RoundIterator(Solution<T> solution) {
            this.solution = solution;
            size = solution.size();
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            T c = solution.get(count);
            if (++count == size) count = 0;
            return c;
        }

        @Override
        public void remove() throws ConcurrentModificationException {
            try {
                Iterator.super.remove();
            } catch(Exception e) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
