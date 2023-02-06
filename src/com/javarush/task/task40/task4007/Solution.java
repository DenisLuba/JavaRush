package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Solution {
    public static void main(String[] args) throws ParseException {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        String[] strings = date.trim().split(" ");

        if (strings[0].contains(".")) {
            calendar.setTime(new SimpleDateFormat("dd.MM.yyyy").parse(strings[0].trim()));
            printDateParameters(calendar);
        }
        if (strings[strings.length - 1].contains(":")) {
            calendar.setTime(new SimpleDateFormat("HH:mm:ss").parse(strings[strings.length - 1].trim()));
            printTimeParameters(calendar);
        }
    }

    private static void printDateParameters(Calendar calendar) {
        System.out.println("День: " + calendar.get(Calendar.DATE) + "\n" +
                "День недели: " + (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1) + "\n" +
                "День месяца: " + calendar.get(Calendar.DAY_OF_MONTH) + "\n" +
                "День года: " + calendar.get(Calendar.DAY_OF_YEAR) + "\n" +
                "Неделя месяца: " + calendar.get(Calendar.WEEK_OF_MONTH) + "\n" +
                "Неделя года: " + calendar.get(Calendar.WEEK_OF_YEAR) + "\n" +
                "Месяц: " + (calendar.get(Calendar.MONTH) + 1) + "\n" +
                "Год: " + calendar.get(Calendar.YEAR));
    }

    private static void printTimeParameters(Calendar calendar) {
        System.out.println("AM или PM: " + (calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM") + "\n" +
                "Часы: " + calendar.get(Calendar.HOUR) + "\n" +
                "Часы дня: " + calendar.get(Calendar.HOUR_OF_DAY) + "\n" +
                "Минуты: " + calendar.get(Calendar.MINUTE) + "\n" +
                "Секунды: " + calendar.get(Calendar.SECOND));
    }
}
