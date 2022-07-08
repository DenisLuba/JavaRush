package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Solution {
    public static void main(String[] args) throws IOException {
        AtomicInteger numberOfFiles = new AtomicInteger(0);
        AtomicInteger numberOfDirectories = new AtomicInteger(-1);
        AtomicLong sizeFiles = new AtomicLong(0);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String pathString = reader.readLine();
            Path path = Paths.get(pathString);
            if (!Files.isDirectory(path)) {
                System.out.println(pathString + " - не папка");
            } else {
                Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attributes) {
                        numberOfDirectories.incrementAndGet();
                        return FileVisitResult.CONTINUE;
                    }
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                        numberOfFiles.incrementAndGet();
                        sizeFiles.addAndGet(attributes.size());
                        return FileVisitResult.CONTINUE;
                    }
                });
                System.out.printf("Всего папок - %d%nВсего файлов - %d%nОбщий размер - %d",
                        numberOfDirectories.intValue(), numberOfFiles.intValue(), sizeFiles.intValue());
            }
        }
    }
}
