package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

interface Command {
    String pathToResources = CashMachine.class.getPackage().getName() +".resources.";
    void execute() throws InterruptOperationException;
}
