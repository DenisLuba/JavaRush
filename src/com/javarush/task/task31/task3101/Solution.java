package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        String path = args[0];
        File filePath = new File(path);

        String resultFileAbsolutePath = args[1];
        File source = new File(resultFileAbsolutePath);

        String pathDestinationFile = source.getParent() + "allFilesContent.txt";
        File destination = new File(pathDestinationFile);

        if (!FileUtils.isExist(destination)) {
            FileUtils.renameFile(source, destination);
            source = destination;
        }

        StringBuilder builder = new StringBuilder();
        String result = intoFiles(filePath, builder);

        try (FileOutputStream fileOutputStream = new FileOutputStream(source, true)) {
            fileOutputStream.write(result.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {}
    }

    static String intoFiles(File path, StringBuilder builder) {

        for (File file : path.listFiles()) {
            if (file.isDirectory()) intoFiles(file, builder);
            else if (file.length() <= 50) {
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    int i;
                    while ((i = fileInputStream.read()) != -1) {
                        builder.append((char) i);
                    }
                    builder.append('\n');
                } catch (IOException e) {
                }
            }
        }
        return builder.toString().trim();
    }
}
