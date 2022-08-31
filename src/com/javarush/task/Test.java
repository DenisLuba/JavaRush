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
        byte[] d = new byte[] {90, 71, -47, 42, 46, 63, -97, -20, -14, -39, -70, 31, -39, -127, 82, -21};

        StringBuilder s = new StringBuilder();

        for (byte b : d) s.append(Integer.toHexString(b));
        System.out.println(s);
    }
}
