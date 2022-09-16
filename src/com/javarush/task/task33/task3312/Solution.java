package com.javarush.task.task33.task3312;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        Zoo.Dog dog = new Zoo.Dog("doggy");
        Zoo.Cat cat = new Zoo.Cat("catty");
        Zoo zoo = new Zoo();
        zoo.animals.add(dog);
        zoo.animals.add(cat);

        String result = new ObjectMapper().writeValueAsString(zoo);

        System.out.println(result);
    }
}