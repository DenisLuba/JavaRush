package com.javarush.task.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {


    public static void main(String[] args) throws ParseException {
        String expression_1 = "get f_1 for f_2 = \"f_3 f_3 f_3\" and date between \"f_4 f_4\" and \"f_5:f_5\"";
        String expression_2 = "get f_1 for f_2 = \"f_3 f_3 f_3\"";
        String expression_3 = "get f_1";
    }

    private static String[] getFields(String expression) {
        String[] array;

        if (!expression.contains("=")) {
            array = new String[1];
            array[0] = expression
                    .toLowerCase()
                    .replaceFirst("get ", "");
        } else if (!expression.contains("and date between")){
            array = new String[3];
            String pattern = "get (\\S+) for (\\S+) = (.+)";
            array[0] = expression
                    .replaceFirst(pattern, "$1");
            array[1] = expression
                    .replaceFirst(pattern, "$2");
            array[2] = expression
                    .replaceFirst(pattern, "$3")
                    .replaceAll("\"", "");
        } else {
            array = new String[5];
            String pattern = "get (\\S+) for (\\S+) = (.+) and date between (.+) and (.+)";
            array[0] = expression
                    .replaceFirst(pattern, "$1");
            array[1] = expression
                    .replaceFirst(pattern, "$2");
            array[2] = expression
                    .replaceFirst(pattern, "$3")
                    .replaceAll("\"", "");
            array[3] = expression
                    .replaceFirst(pattern, "$4")
                    .replaceAll("\"", "");
            array[4] = expression
                    .replaceFirst(pattern, "$5")
                    .replaceAll("\"", "");
        }

        return array;
    }
}