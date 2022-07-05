package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> result = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        queue.add(new File(root));
        File directory;
        while ((directory = queue.poll()) != null) {
            if (directory.isDirectory())
                for (File file : Objects.requireNonNull(directory.listFiles()))
                    queue.offer(file);
            else result.add(directory.getAbsolutePath());
        }
        return result;
    }

    public static void main(String[] args) throws IOException {

    }
}
