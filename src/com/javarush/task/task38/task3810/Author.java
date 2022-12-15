package com.javarush.task.task38.task3810;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public @interface Author {
    String value();
    Position position() default Position.OTHER;
}
