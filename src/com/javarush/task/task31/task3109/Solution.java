package com.javarush.task.task31.task3109;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties properties = new Properties();
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(Paths.get(fileName).toFile()))) {
            if (fileName.endsWith(".xml")) {
                properties.loadFromXML(input);
            } else {
                properties.load(input);
            }
        } catch(IOException e) {
            return properties;
        }
        return properties;
    }
}
