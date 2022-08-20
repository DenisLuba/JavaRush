package com.javarush.task;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Test {
    public static void main(String[] args) {
        int size = 10;
        int x = size + 1, level = -1;
        for(; x != 0; level++) x /= 2;
        System.out.println(level);
        System.out.println(Math.pow(2, level));
        System.out.println();

        int parentNum = size % 2 == 0 ? (size - 1) >> 1 : size >> 1;

        System.out.println(parentNum);

    }
}
