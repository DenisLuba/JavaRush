package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ResourceBundle;
import java.util.stream.Collectors;

class InfoCommand implements Command  {

    private final ResourceBundle res = ResourceBundle.getBundle(pathToResources + "info_en");

    @Override
    public void execute() {
        String info = CurrencyManipulatorFactory
                .getAllCurrencyManipulators()
                .stream()
                .filter(CurrencyManipulator::hasMoney)
                .map(manipulator -> String.format("%s - %d", manipulator.getCurrencyCode(), manipulator.getTotalAmount()))
                .collect(Collectors.joining("\n"));

        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(info.length() > 0 ? info :
                res.getString("no.money"));
    }
}
