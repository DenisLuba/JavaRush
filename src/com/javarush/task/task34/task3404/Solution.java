package com.javarush.task.task34.task3404;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); // Expected output: 0.5 6

    }

    public void recurse(final String expression, int countOperation) {
        System.out.println(expression);

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
            Double grade = Math.pow(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
            String result = String.format("%.2f", grade).replace(",", ".");
            newExpression = newExpression.substring(0, matcher.start()) + result + newExpression.substring(matcher.end());
            recurse(newExpression, ++countOperation);
            return;
        }

        // multiplication of numbers
        pattern = Pattern.compile(number + "\\*" + number);
        matcher = pattern.matcher(newExpression);
        if (matcher.find()) {
            String string = newExpression.substring(matcher.start(), matcher.end());
            String[] values = string.split("\\*");
            Double product = Double.parseDouble(values[0]) * Double.parseDouble(values[1]);
            String result = String.format("%.2f", product).replace(",", ".");
            newExpression = newExpression.substring(0, matcher.start()) + result + newExpression.substring(matcher.end());
            recurse(newExpression, ++countOperation);
            return;
        }

        // dividing numbers
        pattern = Pattern.compile(number + "/" + number);
        matcher = pattern.matcher(newExpression);
        if (matcher.find()) {
            String string = newExpression.substring(matcher.start(), matcher.end());
            String[] values = string.split("/");
            Double quotient = Double.parseDouble(values[0]) / Double.parseDouble(values[1]);
            String result = String.format("%.2f", quotient).replace(",", ".");
            newExpression = newExpression.substring(0, matcher.start()) + result + newExpression.substring(matcher.end());
            recurse(newExpression, ++countOperation);
            return;
        }

        // sum of differen of numbers
        pattern = Pattern.compile(number + "\\+" + number);
        matcher = pattern.matcher(newExpression);
        if (matcher.find()) {
            String string = newExpression.substring(matcher.start(), matcher.end());
            String[] values = string.split("\\+");
            Double sum = Double.parseDouble(values[0]) + Double.parseDouble(values[1]);
            String result = String.format("%.2f", sum).replace(",", ".");
            newExpression = newExpression.substring(0, matcher.start()) + result + newExpression.substring(matcher.end());
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
            Double difference = Double.parseDouble(values.get(0)) - Double.parseDouble(values.get(1));
            String result = String.format("%.2f", difference).replace(",", ".");
            newExpression = newExpression.substring(0, matcher.start()) + result + newExpression.substring(matcher.end());
            recurse(newExpression, ++countOperation);
            return;
        }

        // sine
        pattern = Pattern.compile("sin" + number);
        matcher = pattern.matcher(newExpression);
        if (matcher.find()) {
            Double sin = Math.sin(Math.toRadians(Double.parseDouble(matcher.group().replace("sin", ""))));
            String result = String.format("%.2f", sin).replace(",", ".");
            newExpression = newExpression.substring(0, matcher.start()) + result + newExpression.substring(matcher.end());
            recurse(newExpression, ++countOperation);
            return;
        }

        // cosine
        pattern = Pattern.compile("cos" + number);
        matcher = pattern.matcher(newExpression);
        if (matcher.find()) {
            Double cos = Math.cos(Math.toRadians(Double.parseDouble(matcher.group().replace("cos", ""))));
            String result = String.format("%.2f", cos).replace(",", ".");
            newExpression = newExpression.substring(0, matcher.start()) + result + newExpression.substring(matcher.end());
            recurse(newExpression, ++countOperation);
            return;
        }

        // tangent
        pattern = Pattern.compile("tan" + number);
        matcher = pattern.matcher(newExpression);
        if (matcher.find()) {
            Double tan = Math.tan(Math.toRadians(Double.parseDouble(matcher.group().replace("tan", ""))));
            String result = String.format("%.2f", tan).replace(",", ".");
            newExpression = newExpression.substring(0, matcher.start()) + result + newExpression.substring(matcher.end());
            recurse(newExpression, ++countOperation);
            return;
        }
        System.out.println(newExpression + " " + countOperation);
    }

    public Solution() {}
}
