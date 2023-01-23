package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleHelper {

//    writing a message to the console
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message) {
        System.out.println(message);
    }

//    reading a message from the console
    public static String readString() throws InterruptOperationException {
        StringBuilder message = new StringBuilder();
        try {
            message.append(bis.readLine());
            if (message.toString().equalsIgnoreCase("EXIT")) throw new InterruptOperationException();
        } catch (IOException ignore) {}
        return message.toString();
    }

//    asks for the currency code and returns it in uppercase
    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage("Enter the currency code:");
        String code = "";
        while ((code = readString()).length() != 3)
            writeMessage("The data is incorrect.\nTry again.");

        return code.toUpperCase();
    }

//    asks for the denomination and number of banknotes and returns an array of these values
    public static String[] getValidTwoDigits(String currentCode) throws InterruptOperationException {
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

//    asks for the number of the operation and returns the operation
    public static Operation askOperation() throws InterruptOperationException {
        writeMessage("Enter the number of the operation:");
        String message = IntStream.range(0, Operation.values().length)
                .boxed()
                .map(i -> String.format("%d - %s", i + 1, Operation.values()[i]))
                .collect(Collectors.joining("\n"));
        writeMessage(message);

        return getOperation(message);
    }

//   helps the method adkOperation()
    private static Operation getOperation(String message) throws InterruptOperationException {
        String enteredText;
        while (true) {
            if ((enteredText = readString()).matches("^\\d$")) {
                int operationNumber = Integer.parseInt(enteredText);
                if (operationNumber <= Operation.values().length && operationNumber > 0)
                    return Operation.getAllowableOperationByOrdinal(operationNumber);
            }
            writeMessage("The data is incorrect.\nTry again.");
            writeMessage(message);
        }
    }
}
