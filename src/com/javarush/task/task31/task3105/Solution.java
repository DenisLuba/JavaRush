package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Solution {
    public static void main(String[] args) throws IOException {
        Path file = Paths.get(args[0]);
        Path zipFile = Paths.get(args[1]);
        addFileToZip(file, zipFile);
    }

    public static void addFileToZip(Path file, Path zipFile) throws IOException {
        Path tempFile = zipFile.getParent().resolve("tempFile");
        Files.createDirectory(tempFile);
        decompress(zipFile, tempFile);
        Files.delete(zipFile);
        addFileToFolder(file, tempFile);
        compress(tempFile, zipFile);
        Files.walkFileTree(tempFile, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }
        });
        Files.walkFileTree(tempFile, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    // рабочий метод
    private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024 * 1024];
        int bufferSize;
        while ((bufferSize = inputStream.read(buffer)) != -1) outputStream.write(buffer, 0, bufferSize);
    }

    // рабочий метод
    private static void compress (Path source, Path archive) throws IOException {
        try (ZipOutputStream output = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(archive.toFile())))) {
            Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file.toFile()))) {
                        output.putNextEntry(new ZipEntry(source.relativize(file).toString())); // кладем в ZipOutputStream новую ZipEntry
                        copy(input, output);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    // рабочий метод
    private static void decompress (Path archive, Path destination) throws IOException {
        try (ZipInputStream input = new ZipInputStream(new BufferedInputStream(new FileInputStream(archive.toFile())), Charset.forName("windows-1251"))) {
            ZipEntry entry;
            while ((entry = input.getNextEntry()) != null) { // начинаем цикл, в котором будем получать новые энтри, пока они не закончатся.
                Path filePath = destination.resolve(entry.getName()); // объединяем путь до папки назначения и относительный путь энтри.

                if (entry.isDirectory() && !Files.exists(filePath)) // если энтри - это папка и она пока не создана,
                    Files.createDirectories(filePath); // то создаем;
                else if (!entry.isDirectory() && !Files.exists(filePath)) { // если энтри - это файл и она пока не создана, то
                    if (!Files.exists(filePath.getParent())) // (если родительской директории для файла нет,
                        Files.createDirectory(filePath.getParent()); // то создаем ее)
                    Files.createFile(filePath); // создаем файл, и
                    // копируем данные в этот файл из архива.
                    try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(filePath.toFile()))) {
                        copy(input, output);
                    }
                }
            }
        }
    }

    // рабочий метод
    private static void addFileToFolder(Path newFile, Path directory) throws IOException {
        final Path[] newPath = {directory.resolve(Paths.get("new")).resolve(newFile.getFileName())};
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.getFileName().equals(newFile.getFileName())) {
                    newPath[0] = file;
                    Files.delete(file);
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }
        });
        if(!Files.exists(newPath[0].getParent())) Files.createDirectory(newPath[0].getParent());
        Files.move(newFile, newPath[0]);
    }
}
