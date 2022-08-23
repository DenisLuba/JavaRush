package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String[] args) {
        try (RandomAccessFile accessFile = new RandomAccessFile(args[0], "rw")) {
            accessFile.seek(Integer.parseInt(args[1]));
            accessFile.writeBytes(args[2]);
        } catch (IOException e) {
        }

    }
}
