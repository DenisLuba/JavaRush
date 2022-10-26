package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            StringBuilder builder = new StringBuilder();
            while (bufferedReader.ready())
                builder.append(bufferedReader.readLine()
                        .toLowerCase()
                        .replaceAll("[^a-z]", ""));

//            TreeSet<Character> treeSet = builder
//                    .chars()                                                  // IntStream - последовательность примитивных элементов
//                    .mapToObj(c -> (char) c)                                  // <U> Stream<U> - последовательность элементов char
//                    .collect(Collectors.toCollection(TreeSet::new));          // изменяет поток, превращая его в TreeSet

            char[] chars = builder.toString().toCharArray();
            TreeSet<Character> treeSet = new TreeSet<>();

            for(char ch : chars)
                treeSet.add(ch);

            if (treeSet.size() < 5)
                for(Character ch : treeSet) System.out.print(ch);
            else
                for (int i = 0; i < 5; i++)
                    System.out.print(treeSet.pollFirst());
        }
    }
}
