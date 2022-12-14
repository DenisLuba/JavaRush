package com.javarush.task.task38.task3804;

import java.util.Locale;

public class FactoryClass {
    public static Throwable getWrong(Enum<?> e) {
        if (e == null) return new IllegalArgumentException();
        String message = Character.toUpperCase(e.name().charAt(0))
                + e.name().substring(1).toLowerCase().replaceAll("_", " ");

        if (e.getClass().equals(ApplicationExceptionMessage.class)) {
            return new Exception(message);
        } else if (e.getClass().equals(UserExceptionMessage.class)) {
            return new Error(message);
        } else if (e.getClass().equals(DatabaseExceptionMessage.class)) {
            return new RuntimeException(message);
        } else return new IllegalArgumentException();
    }
}
