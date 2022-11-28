package com.javarush.task.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {


    public static void main(String[] args) throws ParseException {
        String pattern = "^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(\\s+|\\t)" +
                "(.+)(\\s+|\\t)" +
                "(\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\s\\d{1,2}:\\d{1,2}:\\d{1,2})(\\s+|\\t)" +
                "([A-Z_]+\\s?\\d*)(\\s+|\\t)" +
                "([A-Z]+)$";

        String str = "120.120.120.122 Amigo   29.2.2028 5:4:7 SOLVE_TASK 18   OK";
        System.out.println(str.replaceAll(pattern, "$5"));
    }
}