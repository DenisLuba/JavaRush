package com.javarush.task.task38.task3810;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public @interface Date {
    int year();
    int month();
    int day();
    int hour();
    int minute();
    int second();
}
