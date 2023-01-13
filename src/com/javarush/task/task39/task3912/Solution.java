package com.javarush.task.task39.task3912;

import org.junit.Assert;
import org.junit.Test;

public class Solution {
    public static void main(String[] args) {
    }

    public static int maxSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int longestSide = 0;

        for (int y = 1; y < matrix.length; y++)
            for (int x = 1; x < matrix[0].length; x++)
                if (matrix[y][x] == 1) {
                    int smallestCell = Math.min(matrix[y][x - 1], matrix[y - 1][x]);
                    smallestCell = Math.min(smallestCell, matrix[y - 1][x - 1]);
                    matrix[y][x] = ++smallestCell;
                    longestSide = Math.max(longestSide, smallestCell);
                }

        return longestSide * longestSide;
    }

    @Test
    public void testMaxSquare() {
        int[][] matrix = {
                {0, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 1},
                {0, 1, 1, 0, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1}
        };
        Assert.assertEquals(maxSquare(matrix), 25);
    }
}
