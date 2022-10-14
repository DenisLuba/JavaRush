package com.javarush.task.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {
    public static void main(String[] args) {
        int i = new BigDecimal(4.000001).setScale(0, RoundingMode.UP).intValue();
        System.out.println(i);
    }

}