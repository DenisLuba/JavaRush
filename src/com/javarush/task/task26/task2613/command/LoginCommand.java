package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private final ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private final ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while(true) {
            ConsoleHelper.writeMessage(res.getString("specify.card.number"));
            String number = ConsoleHelper.readString();
            ConsoleHelper.writeMessage(res.getString("specify.card.code"));
            String pin = ConsoleHelper.readString();
            if (validCreditCards.containsKey(number) &&
                    validCreditCards.getString(number).equals(pin)) {
                String success = String.format(res.getString("success.format"), number);
                ConsoleHelper.writeMessage(success);
                return;
            }
            String failure = String.format(res.getString("not.verified.format"), number);
            ConsoleHelper.writeMessage(failure);
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
        }
    }
}
