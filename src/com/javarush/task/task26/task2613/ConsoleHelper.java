package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        StringBuilder message = new StringBuilder();
        try {
            message.append(bis.readLine());
        } catch (IOException ignore) {}
        return message.toString();
    }
}
