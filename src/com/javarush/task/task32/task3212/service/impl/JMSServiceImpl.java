package com.javarush.task.task32.task3212.service.impl;

import com.javarush.task.task32.task3212.service.Service;

public class JMSServiceImpl implements Service {
    @Override
    public String getName() {
        return "JMSService";
    }

    @Override
    public void execute() {
        System.out.println("Executing the JMSService");
    }
}
