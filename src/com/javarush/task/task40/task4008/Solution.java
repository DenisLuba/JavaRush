package com.javarush.task.task40.task4008;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) {
        printDate("9.10.2017 5:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        String[] strings = date.trim().split(" ");

        if (strings[0].contains(".")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            LocalDate localDate = LocalDate.parse(strings[0].trim(), formatter);
            printDateParameters(localDate);
        }
        if (strings[strings.length - 1].contains(":")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:m:s");
            LocalTime localTime = LocalTime.parse(strings[strings.length - 1].trim(), formatter);
            printTimeParameters(localTime);
        }
    }

    private static void printDateParameters(LocalDate localDate) {
        System.out.println("День: " + localDate.getDayOfMonth() + "\n" +
                "День недели: " + localDate.getDayOfWeek().getValue() + "\n" +
                "День месяца: " + localDate.getDayOfMonth() + "\n" +
                "День года: " + localDate.getDayOfYear() + "\n" +
                "Неделя месяца: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfMonth()) + "\n" +
                "Неделя года: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfYear()) + "\n" +
                "Месяц: " + localDate.getMonth().getValue() + "\n" +
                "Год: " + localDate.getYear());
    }

    private static void printTimeParameters(LocalTime localTime) {
        System.out.println("AM или PM: " + (localTime.get(ChronoField.AMPM_OF_DAY) == 0 ? "AM" : "PM") + "\n" +
                "Часы: " + localTime.get(ChronoField.HOUR_OF_AMPM) + "\n" +
                "Часы дня: " + localTime.getHour() + "\n" +
                "Минуты: " + localTime.getMinute() + "\n" +
                "Секунды: " + localTime.getSecond());
    }
}
