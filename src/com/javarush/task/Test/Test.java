package com.javarush.task.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {


    public static void main(String[] args) throws ParseException {
        Date date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("21.10.2021 19:45:25");
        System.out.println(date);

        date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("29.2.2028 5:4:7");
        System.out.println(date);
    }
}