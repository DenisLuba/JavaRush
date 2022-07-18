package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipInputStream;

public class Solution {
    public static void main(String[] args) throws IOException {
        Path resultPath = Paths.get(args[0]);

        if (Files.exists(resultPath.getParent()) && !Files.exists(resultPath)) Files.createFile(resultPath);
        if (!Files.exists(resultPath.getParent())) {
            Files.createDirectories(resultPath.getParent());
            Files.createFile(resultPath);
        }

        TreeSet<String> paths = new TreeSet<>(Arrays.asList(args).subList(1, args.length));

        byte[] buffer = new byte[2048];

        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(resultPath.toFile(), true))) {
            for (String path : paths) {
                try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path));
                     ZipInputStream zipInputStream = new ZipInputStream(bufferedInputStream)) {

                    while (zipInputStream.getNextEntry() != null) {
                        int len;
                        while ((len = zipInputStream.read(buffer)) > 0)
                            outputStream.write(buffer, 0, len);
                    }
                }
            }
        }
    }
}
