package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Solution {
    public static void main(String[] args) throws IOException {
        Path file = Paths.get(args[0]);
        if (!Files.exists(file.getParent()))
            Files.createDirectories(file.getParent());

        try (FileOutputStream zipFile = new FileOutputStream(file.getParent().toString() + "file.zip", true);
        ZipOutputStream zip = new ZipOutputStream(zipFile)) {


            zip.putNextEntry(new ZipEntry(file.getFileName().toString()));

            Set<String> namesOfFileParts = new TreeSet<>();
            for (int i = 1; i < args.length; i++)
                namesOfFileParts.add(args[i]);

            for (String namePart : namesOfFileParts) {
                try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(namePart))) {
                    while ((zipInputStream.getNextEntry()) != null) {
                        Files.copy()
                    }
                }


            }
        }
    }
}
