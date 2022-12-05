package com.javarush.task.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {


    public static void main(String[] args) throws ParseException {


        String[] split = "get ip for user = Amigo Sergeevich".split("=");
        String attribute = split[1].trim();
        String field_1 = split[0].trim().toLowerCase().replaceFirst("get (\\S+) for (\\S+)", "$1");
        String field_2 = split[0].trim().toLowerCase().replaceFirst("get (\\S+) for (\\S+)", "$2");

        System.out.println(attribute + "\n" + field_1 + "\n" + field_2);
    }
}