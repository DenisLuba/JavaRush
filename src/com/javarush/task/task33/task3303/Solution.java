package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return (T) mapper.readValue(new File(fileName), clazz);
    }

    public static void main(String[] args) throws IOException {
        Cat cat = convertFromJsonToNormal("D:\\Temp\\Solution.txt", Cat.class);
        System.out.println(cat);
    }

    @JsonAutoDetect
    public static class Cat {
        public String name;
        public int age;
        public int weight;
        Cat(){}

        @Override
        public String toString() {
            return String.format("name - %s\nage = %d\nweight = %d", name, age, weight);
        }
    }
}
