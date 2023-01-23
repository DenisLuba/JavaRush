package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Operation operation = null;

        do {
            try {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } catch(InterruptOperationException e) {
                operation = Operation.EXIT;
                ConsoleHelper.writeMessage("Goodbye!");
            }
        } while (operation != Operation.EXIT);
    }
}