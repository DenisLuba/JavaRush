package com.javarush.task.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;

public class GetJSONMessage {
    public static void main(String[] args) throws IOException {
        String jsonString = "{\"name\":\"Martin\",\"age\":11,\"weight\":6}";
        StringReader reader = new StringReader(jsonString);

        ObjectMapper mapper = new ObjectMapper();

        Cat cat = mapper.readValue(reader, Cat.class);

        System.out.printf("name - %s\nage - %s\nweight - %s", cat.name, cat.age, cat.weight);
    }

    @JsonAutoDetect
    static class Cat {
        public String name;
        public int age;
        public int weight;
        Cat(){}
    }
}
