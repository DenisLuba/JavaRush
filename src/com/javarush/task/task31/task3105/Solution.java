package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Solution {
    public static void main(String[] args) throws IOException {
        String source = "C:\\Users\\support\\Desktop\\Solution\\Folder.zip";
        Path sourceFolder = Paths.get(source.replace(".zip", ""));
        String destination = "";
        ZipFile archive = new ZipFile(source);


        Enumeration<? extends ZipEntry> entries = archive.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            Path tempFile;
            Path file = Paths.get(sourceFolder + "\\" + entry.getName());
            if (!Files.exists(file)) tempFile = entry.isDirectory() ? Files.createDirectory(file) : Files.createFile(file);
            else tempFile = file;
            if (entry.isDirectory()) continue;

            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(tempFile.toFile()));
            zipOutputStream.putNextEntry(entry);
            copy(archive.getInputStream(entry), zipOutputStream);
        }

    }

    public static void copy(InputStream inputStream, OutputStream outputStream) {
        byte[] buffer = new byte[1024 * 1024];
        int bufferSize;
        try {
            while ((bufferSize = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bufferSize);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
