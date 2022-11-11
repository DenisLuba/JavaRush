package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    Path path;

    public FileBucket() {
        try{
            path = Files.createTempFile("bucket_", ".temp");
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }

    // getFileSize
    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        return -1;
    }

    public void putEntry(Entry entry) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(entry);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry() {
        if (getFileSize() > 0) {
            try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path))) {
                return (Entry) inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                ExceptionHandler.log(e);
            }
        }

        return null;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }
}
