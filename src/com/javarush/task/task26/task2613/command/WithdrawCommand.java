package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);

        String amount = "";
        while (true) {
            ConsoleHelper.writeMessage("Enter the amount:");
            if (!(amount = ConsoleHelper.readString()).matches("^\\d+$")) {
                ConsoleHelper.writeMessage("The data is incorrect. Try again.");
                continue;
            }
            if (manipulator.isAmountAvailable(Integer.parseInt(amount))) {
                try {
                    manipulator
                            .withdrawAmount(Integer.parseInt(amount))
                            .forEach((key, value) -> System.out.printf("\t%d - %d\n", key, value));
                    break;
                } catch(NotEnoughMoneyException e) {
                    ConsoleHelper.writeMessage("You don't have enough money.");
                }
            }
        }
    }
}
