package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

public class LoginCommand implements Command{

    private final String CARD_NUMBER = "123456789012";
    private final String PIN_CODE = "1234";

    @Override
    public void execute() throws InterruptOperationException {
        String pin = "";
        String number = "";

        while(true) {
            ConsoleHelper.writeMessage("Please, enter the card number:");
            number = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Please, enter the pin code:");
            pin = ConsoleHelper.readString();
            if (number.equals(CARD_NUMBER) && pin.equals(PIN_CODE)) break;
            ConsoleHelper.writeMessage("The data is incorrect. Try again.");
        }
        ConsoleHelper.writeMessage("The data is valid.");
    }
}
