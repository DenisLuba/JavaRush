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

    public static String askCurrencyCode() {
        String code = "";
        while ((code = readString()).length() != 3)
            writeMessage("The data is incorrect.\nTry again.");

        return code.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currentCode) {
        String[] arr;
        while(true) {
            if ((arr = readString().split(" ")).length == 2) {
                for (String str : arr) {
                    if str.matches("^\\d$");
                }
            }
            return new String[2];
        }
    }

    private boolean isNumber(String str) {
        return str.matches("^\\d$");
    }
}
