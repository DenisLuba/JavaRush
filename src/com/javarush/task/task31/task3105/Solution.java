package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Solution {
    public static void main(String[] args) throws IOException {
        System.out.println(unarchive("D:\\D.zip"));
    }

    public static Path unarchive(String zipFile) throws IOException {
        Path resultPath = Files.createTempDirectory("temp-");
        try (ZipInputStream input = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)))) {
            ZipEntry entry;
            while ((entry = input.getNextEntry()) != null) {
                try (FileOutputStream output = new FileOutputStream(resultPath.toFile() + "\\" + entry.getName())) {
                    copy(input, output);
                }
            }
        }
        return resultPath;
    }

    public static void archive(String result, Path path) {

    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024 * 1024];
        int size;
        while ((size = in.read(buffer)) != -1)
            out.write(buffer, 0, size);
    }
}


