package com.javarush.task.task32.task3202;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("C:\\Users\\support\\Desktop\\Solution\\Doc.txt"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter writer = new StringWriter();
        if (is != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                while (reader.ready())
                    writer.write(reader.readLine());
            }
        }
        return writer;
    }
}
