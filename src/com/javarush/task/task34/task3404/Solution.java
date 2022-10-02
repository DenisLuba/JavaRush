package com.javarush.task.task34.task3404;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); // Expected output: 0.5 6

        String[][] data = new String[][]{
                {"sin(2*(-5+1.5*4)+28)", "0.5 6"},
                {"tan(2025 ^ 0.5)", "1 2"},
                {"1+(1+(1+1)*(1+1))*(1+1)+1", "12 8"},
                {"-2^(-2)", "-0.25 3"},
                {"-(-2^(-2))+2+(-(-2^(-2)))", "2.5 10"},
                {"(-2)*(-2)", "4 3"},
                {"sin(-30)", "-0.5 2"},
                {"cos(-30)", "0.87 2"},
                {"tan(-30)", "-0.58 2"},
                {"2+8*(9/4-1.5)^(1+1)", "6.48 6"},
                {"tan(44+sin(89-cos(180)^2))", "1 6"},
                {"-cos(180)^2", "-1 3"},
                {"0+0.304", "0.3 1"},
                {"-0", "0 1"},
                {"sin(3.14/2)^2 + cos(3.14/2)^2","1 7"},
                {"sin(2*55) - 2*sin(55)*cos(55)","0 7"}
        };

        for (String[] o : data) {
            solution.recurse(o[0], 0);
        }
    }

    public void recurse(final String expression, int countOperation) {
//        System.out.println(expression);

        String number = "-?\\d+\\.?\\d*";
        String newExpression = expression.replace(" ", "");

        Pattern pattern;
        Matcher matcher;

        // number in parentheses
        pattern = Pattern.compile("\\(" + number + "\\)");
        matcher = pattern.matcher(newExpression);
        while (matcher.find()) {
            String oldString = matcher.group();
            String newString = oldString.replace("(", "").replace(")", "");
            newExpression = newExpression.replace(oldString, newString);
        }

        // exponentiation of a number
        pattern = Pattern.compile(number + "\\^" + number);
        matcher = pattern.matcher(newExpression);
        if (matcher.find()) {
            String string = newExpression.substring(matcher.start(), matcher.end());
            String[] values = string.split("\\^");
            double grade = Math.pow(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
            newExpression = newExpression.substring(0, matcher.start()) + grade + newExpression.substring(matcher.end());
            recurse(newExpression, ++countOperation);
            return;
        }

        // multiplication of numbers
        pattern = Pattern.compile(number + "\\*" + number);
        matcher = pattern.matcher(newExpression);
        if (matcher.find()) {
            String string = newExpression.substring(matcher.start(), matcher.end());
            String[] values = string.split("\\*");
            double product = Double.parseDouble(values[0]) * Double.parseDouble(values[1]);
            newExpression = newExpression.substring(0, matcher.start()) + product + newExpression.substring(matcher.end());
            recurse(newExpression, ++countOperation);
            return;
        }

        // dividing numbers
        pattern = Pattern.compile(number + "/" + number);
        matcher = pattern.matcher(newExpression);
        if (matcher.find()) {
            String string = newExpression.substring(matcher.start(), matcher.end());
            String[] values = string.split("/");
            double quotient = Double.parseDouble(values[0]) / Double.parseDouble(values[1]);
            newExpression = newExpression.substring(0, matcher.start()) + quotient + newExpression.substring(matcher.end());
            recurse(newExpression, ++countOperation);
            return;
        }

        // sum of differen of numbers
        pattern = Pattern.compile(number + "\\+" + number);
        matcher = pattern.matcher(newExpression);
        if (matcher.find()) {
            String string = newExpression.substring(matcher.start(), matcher.end());
            String[] values = string.split("\\+");
            double sum = Double.parseDouble(values[0]) + Double.parseDouble(values[1]);
            newExpression = newExpression.substring(0, matcher.start()) + sum + newExpression.substring(matcher.end());
            recurse(newExpression, ++countOperation);
            return;
        }

        // difference of numbers
        pattern = Pattern.compile(number + "-" + number);
        matcher = pattern.matcher(newExpression);
        if (matcher.find()) {
            String string = newExpression.substring(matcher.start(), matcher.end());
            Matcher m = Pattern.compile(number).matcher(string);
            List<String> values = new ArrayList<>();
            while (m.find())
                values.add(m.group());
            double difference = Double.parseDouble(values.get(0)) - Double.parseDouble(values.get(1));
            newExpression = newExpression.substring(0, matcher.start()) + difference + newExpression.substring(matcher.end());
            recurse(newExpression, ++countOperation);
            return;
        }

        // sine
        pattern = Pattern.compile("sin" + number);
        matcher = pattern.matcher(newExpression);
        if (matcher.find()) {
            double sin = Math.sin(Math.toRadians(Double.parseDouble(matcher.group().replace("sin", ""))));
            newExpression = newExpression.substring(0, matcher.start()) + sin + newExpression.substring(matcher.end());
            recurse(newExpression, ++countOperation);
            return;
        }

        // cosine
        pattern = Pattern.compile("cos" + number);
        matcher = pattern.matcher(newExpression);
        if (matcher.find()) {
            double cos = Math.cos(Math.toRadians(Double.parseDouble(matcher.group().replace("cos", ""))));
            newExpression = newExpression.substring(0, matcher.start()) + cos + newExpression.substring(matcher.end());
            recurse(newExpression, ++countOperation);
            return;
        }

        // tangent
        pattern = Pattern.compile("tan" + number);
        matcher = pattern.matcher(newExpression);
        if (matcher.find()) {
            double tan = Math.tan(Math.toRadians(Double.parseDouble(matcher.group().replace("tan", ""))));
            newExpression = newExpression.substring(0, matcher.start()) + tan + newExpression.substring(matcher.end());
            recurse(newExpression, ++countOperation);
            return;
        }

        Double result = 0.0;
        try {
            result = Double.parseDouble(newExpression);
        } catch (NumberFormatException e) {
            System.out.println("the result is not a number");
        }
        result = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue();

        System.out.println(result + " " + countOperation);
    }

    public Solution() {}
}
