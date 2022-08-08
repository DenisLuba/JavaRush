package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Solution {
    public static void main(String[] args) throws IOException {
        Path destination = Paths.get("D:\\eng");
        Path archive = Paths.get("D:\\ENGLISH.zip");
        decompress(archive, destination);

//        Path sourceFolder = Paths.get(source.replace(".zip", ""));
//        String destination = "";
//        ZipFile archive = new ZipFile(source);
//
//
//        Enumeration<? extends ZipEntry> entries = archive.entries();
//        while (entries.hasMoreElements()) {
//            ZipEntry entry = entries.nextElement();
//            Path tempFile;
//            Path file = Paths.get(sourceFolder + "\\" + entry.getName());
//            if (!Files.exists(file)) tempFile = entry.isDirectory() ? Files.createDirectory(file) : Files.createFile(file);
//            else tempFile = file;
//            if (entry.isDirectory()) continue;
//
//            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(tempFile.toFile()));
//            zipOutputStream.putNextEntry(entry);
//            copy(archive.getInputStream(entry), zipOutputStream);
//        }

    }

    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024 * 1024];
        int bufferSize;

        while ((bufferSize = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bufferSize);
        }
    }

    public static void compress (Path source, Path archive) throws IOException {

    }

    public static void decompress (Path archive, Path destination) throws IOException {
        try (ZipInputStream input = new ZipInputStream(new BufferedInputStream(new FileInputStream(archive.toFile())), Charset.forName("windows-1251"))) {
            ZipEntry entry;
            while ((entry = input.getNextEntry()) != null) {
                Path filePath = destination.resolve(entry.getName());

                if (entry.isDirectory() && !Files.exists(filePath))
                    Files.createDirectories(filePath);
                else if (!entry.isDirectory() && !Files.exists(filePath)) {
                    if (!Files.exists(filePath.getParent()))
                        Files.createDirectory(filePath.getParent());
                    Files.createFile(filePath);
                    try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(filePath.toFile()))) {
                        copy(input, output);
                    }
                }
            }
        }
    }


}
