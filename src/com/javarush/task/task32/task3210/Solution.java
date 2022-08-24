package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String[] args) {
        String fileName = args[0];
        long number = Long.parseLong(args[1]);
        String text = args[2];
        int lengthText = text.getBytes().length;
        byte[] buffer = new byte[lengthText];

        try(RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw")) {
            long lengthFile = randomAccessFile.length();
            randomAccessFile.seek(number);
            randomAccessFile.read(buffer, 0, lengthText);
            String textInFile = new String(buffer);
            //System.out.println(textInFile);
            String result = text.equals(textInFile) ? "true" : "false";
            randomAccessFile.seek(lengthFile);
            randomAccessFile.write(result.getBytes());
        } catch (IOException e) {}
    }
}
