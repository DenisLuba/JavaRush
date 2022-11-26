package com.javarush.task.Test;

public class Test {


    public static void main(String[] args) {
        int count_0 = 0;
        int count_1 = 0;
        int count_2 = 0;

        for(int i = 0; i < 10000; i++) {
            int random = (int) (Math.random() * 3);
            switch (random) {
                case 0 -> count_0++;
                case 1 -> count_1++;
                case 2 -> count_2++;
            }
        }

        System.out.printf("0 = %d\n1 = %d\n2 = %d", count_0, count_1, count_2);
    }
}