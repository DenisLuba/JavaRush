package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ResourceBundle;
import java.util.stream.Collectors;

class InfoCommand implements Command  {

    private final String pathToResources = CashMachine.class.getPackage().getName() + ".resources.info_en";
    private ResourceBundle res = ResourceBundle.getBundle(pathToResources);

    @Override
    public void execute() {
        String info = CurrencyManipulatorFactory
                .getAllCurrencyManipulators()
                .stream()
                .filter(CurrencyManipulator::hasMoney)
                .map(manipulator -> String.format("%s - %d", manipulator.getCurrencyCode(), manipulator.getTotalAmount()))
                .collect(Collectors.joining("\n"));

        ConsoleHelper.writeMessage(res.getString("before") + '\n');
        ConsoleHelper.writeMessage(info.length() > 0 ? info :
                res.getString("no.money"));
    }
}
