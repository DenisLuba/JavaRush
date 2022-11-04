package com.javarush.task.task33.task3310;

import java.util.logging.Handler;

public class ExceptionHandler {
    public static void log(Exception e) {
        Helper.printMessage(e.toString());
    }
}
