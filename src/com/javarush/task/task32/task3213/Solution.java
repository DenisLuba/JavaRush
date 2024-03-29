package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3)); //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        String result = "";
        StringWriter writer = new StringWriter();
        if (reader != null) {
            int ch;
            while ((ch = reader.read()) != -1)
                writer.write(ch + key);
            result = writer.toString();
        }
        return result;
    }
}
