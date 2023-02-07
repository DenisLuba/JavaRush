package com.javarush.task.task40.task4008;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.Calendar;

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        String[] strings = date.trim().split(" ");

        if (strings[0].contains(".")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.parse(strings[0].trim(), formatter);
            printDateParameters(localDate);
        }
        if (strings[strings.length - 1].contains(":")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTime = LocalTime.parse(strings[strings.length - 1].trim(), formatter);
            printTimeParameters(localTime);
        }
    }

    private static void printDateParameters(LocalDate localDate) {
        System.out.println("День: " + localDate.getDayOfMonth() + "\n" +
                "День недели: " + localDate.getDayOfWeek() + "\n" +
                "День месяца: " + localDate.getDayOfMonth() + "\n" +
                "День года: " + localDate.getDayOfYear() + "\n" +
                "Неделя месяца: " + localDate.get(TemporalField.) + "\n" +
                "Неделя года: " + localDate.get(Calendar.WEEK_OF_YEAR) + "\n" +
                "Месяц: " + (localDate.get(Calendar.MONTH) + 1) + "\n" +
                "Год: " + localDate.get(Calendar.YEAR));
    }

    private static void printTimeParameters(LocalTime localTime) {
        System.out.println("AM или PM: " + (localTime.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM") + "\n" +
                "Часы: " + localTime.get(Calendar.HOUR) + "\n" +
                "Часы дня: " + localTime.get(Calendar.HOUR_OF_DAY) + "\n" +
                "Минуты: " + localTime.get(Calendar.MINUTE) + "\n" +
                "Секунды: " + localTime.get(Calendar.SECOND));
    }
}
