package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private String pathToResources = CashMachine.class.getPackage().getName() +".resources.";
    private String pathToResourceVerifiedCards = pathToResources + "verifiedCards";
    private String pathToResourceLogin = pathToResources + "login_en";
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(pathToResourceVerifiedCards, Locale.US);
    private ResourceBundle res = ResourceBundle.getBundle(pathToResourceLogin, Locale.US);
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while(true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String number = ConsoleHelper.readString();
            String pin = ConsoleHelper.readString();
            if (validCreditCards.containsKey(number) &&
                    validCreditCards.getString(number).equals(pin)) {
                String success = String.format(res.getString("success.format"), number);
                ConsoleHelper.writeMessage(success);
                break;
            }
            ConsoleHelper.writeMessage("The data is incorrect. Try again.");
        }
        ConsoleHelper.writeMessage("The data is valid.");
    }
}
