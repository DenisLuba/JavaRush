package com.javarush.task.task34.task3411;

public class Solution {
    public static void main(String[] args) {
        int numRings = 7;
        moveRing('A', 'B', 'C', numRings);
    }

    public static void moveRing(char a, char b, char c, int numRing) {
        if (numRing != 0) {
            moveRing(a, c, b, numRing - 1);
            System.out.println("from " + a + " to " + b);
            moveRing(c, b, a, numRing - 1);
        }
    }
}
