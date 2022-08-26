package com.javarush.task.task32.task3204;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }
    public static ByteArrayOutputStream getPassword() {
        int[] upperBuffer = new int[91 - 65];
        for (int i = 0, j = 65 ; j < 91; i++, j++) upperBuffer[i] = j;
        int[] lowerBuffer = new int[123 - 97];
        for (int i = 0, j = 97; j < 123; i++, j++) lowerBuffer[i] = j;
        int[] digitBuffer = new int[58 - 48];
        for (int i = 0, j = 48; j < 58; i++, j++) digitBuffer[i] = j;

        StringBuilder builder = new StringBuilder();
        Random random = new Random(System.nanoTime());
        for (int i = 0; i < 8; i++) {
            char symbol = 0;
            switch(random.nextInt(3)) {
                case 0 : symbol = (char) upperBuffer[random.nextInt(upperBuffer.length)];
                    break;
                case 1 : symbol = (char) lowerBuffer[random.nextInt(lowerBuffer.length)];
                    break;
                case 2 : symbol = (char) digitBuffer[random.nextInt(digitBuffer.length)];
            }
            builder.append(symbol);
        }

        String password = builder.toString();
        boolean hasUpper = false, hasLower = false, hasDigit = false;
        for (int i : upperBuffer) {
            if (password.contains("" + (char) i)) {
                hasUpper = true;
                break;
            }
        }
        for (int i : lowerBuffer) {
            if (password.contains("" + (char) i)) {
                hasLower = true;
                break;
            }
        }
        for (int i : digitBuffer) {
            if (password.contains("" + (char) i)) {
                hasDigit = true;
                break;
            }
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (!(hasUpper && hasLower && hasDigit)) outputStream = getPassword();
        else {
            try (ByteArrayInputStream inputStream = new ByteArrayInputStream(builder.toString().getBytes());
                 BufferedInputStream reader = new BufferedInputStream(inputStream)) {
                int ch;
                while ((ch = reader.read()) != -1)
                    outputStream.write(ch);
            } catch (IOException e) {
            }
        }
        return outputStream;
    }
}
