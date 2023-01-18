package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

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
        writeMessage("Enter the currency code:");
        String code = "";
        while ((code = readString()).length() != 3)
            writeMessage("The data is incorrect.\nTry again.");

        return code.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currentCode) {
        writeMessage("Enter the denomination and number of banknotes:");
        String[] digits;
        while((digits = Arrays
                .stream(readString().trim().split("\\s+"))
                .filter(string -> string.matches("^\\d+$"))
                .toArray(String[]::new)).length != 2) {

            writeMessage("The data is incorrect.\nTry again.");
        }
        return digits;
    }
}
