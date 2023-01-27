package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command {

    private final String pathToResources = CashMachine.class.getPackage().getName() + ".resources.exit_en";
    private ResourceBundle res = ResourceBundle.getBundle(pathToResources);
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        if (ConsoleHelper.readString().equalsIgnoreCase("y"))
            ConsoleHelper.writeMessage(res.getString("thank.message"));
    }
}
