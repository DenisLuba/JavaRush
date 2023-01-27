package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.ResourceBundle;

class WithdrawCommand implements Command {

    private final ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);

        String amount;
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            if (!(amount = ConsoleHelper.readString().trim()).matches("^\\d+$")) {
                ConsoleHelper.writeMessage(res.getString("data.incorrect"));
                continue;
            }
            if (manipulator.isAmountAvailable(Integer.parseInt(amount))) {
                try {
                    manipulator
                            .withdrawAmount(Integer.parseInt(amount))
                            .forEach((key, value) -> ConsoleHelper
                                    .writeMessage(String
                                            .format(res.getString("before") + '\n' + res.getString("success.format"),
                                                    key * value, code)));
                    return;
                } catch(NotEnoughMoneyException e) {
                    ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                    return;
                }
            }
            ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            return;
        }
    }
}
