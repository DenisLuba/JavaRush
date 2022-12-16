package com.javarush.task.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String myString = in.nextLine();

        Arrays.asList(myString.toCharArray()).stream().distinct().collect(Collectors.toList());


        int sum = 0;
        for (char ch : myString.toUpperCase().toCharArray())
            sum += ch;

        char average = (char) (sum / myString.length());
        System.out.println(average);
    }
}