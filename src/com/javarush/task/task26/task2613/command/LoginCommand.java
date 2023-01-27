package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private String pathToResources = CashMachine.class.getPackage().getName() + ".resources.verifiedCards";

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(pathToResources, Locale.US);

    @Override
    public void execute() throws InterruptOperationException {
        while(true) {
            ConsoleHelper.writeMessage("Please, enter the card number:");
            String number = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Please, enter the pin code:");
            String pin = ConsoleHelper.readString();
            if (validCreditCards.containsKey(number) &&
                    validCreditCards.getString(number).equals(pin)) break;
            ConsoleHelper.writeMessage("The data is incorrect. Try again.");
        }
        ConsoleHelper.writeMessage("The data is valid.");
    }
}
