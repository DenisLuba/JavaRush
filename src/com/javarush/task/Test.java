package com.javarush.task;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Test {
    static int[][] matrix = {
            {1, 1, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 1},
            {1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1}
    };

    static void removeFullLines() {
        int height = matrix.length;
        int width = matrix[0].length;
        //Создаем список для хранения линий
        ArrayList<int[]> lines = new ArrayList<>();

        //Копируем все неполные линии в список.
        for (int i = 0; i < height; i++) {
            //подсчитываем количество единиц в строке - просто суммируем все ее значения
            int count = 0;
            for (int j = 0; j < width; j++) {
                count += matrix[i][j];
            }

            //Если сумма строки не равна ее ширине - добавляем в список
            if (count != width)
                lines.add(matrix[i]);
        }

        //Добавляем недостающие строки в начало списка.
        while (lines.size() < height) {
            lines.add(0, new int[width]);
        }

        //Преобразуем список обратно в матрицу
        matrix = lines.toArray(new int[height][width]);
    }

//    public static void removeFullLines() {
//        int w = matrix[0].length;
//        boolean isFullLine;
//        for (int i = 0; i < matrix.length; i++) {
//            isFullLine = true;
//            for (int j = 0; j < matrix[i].length; j++)
//                if (matrix[i][j] == 0) {
//                    isFullLine = false;
//                    break;
//                }
//            if (isFullLine) matrix[i] = null;
//        }
//
//        if (matrix[0] == null)
//            matrix[0] = new int[w];
//        for (int i = 0; i < matrix.length; i++) {
//
//            if (matrix[i] == null) {
//                if (i > 0) {
//                    int[] rowX = matrix[0].clone();
//                    int[] rowY;
//                    for (int j = 1; j <= i; j++) {
//                        rowY = matrix[j];
//                        matrix[j] = rowX;
//                        rowX = rowY;
//                    }
//                }
//                assert matrix[0] != null;
//                Arrays.fill(matrix[0], 0);
//            }
//        }
//    }

    public static void print() {
        for (int[] rows : matrix) {
            for (int cell : rows)
                System.out.print((cell == 0 ? '.' : 'X') + "  ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        print();
        System.out.println('\n');
        removeFullLines();
        print();
    }
}