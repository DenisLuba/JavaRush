package com.javarush.task.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class SentJSONMessage {
    public static void main(String[] args) throws IOException {
        //создание объекта для сериализации в JSON
        Cat cat = new Cat();
        cat.name = "Murka";
        cat.age = 5;
        cat.weight = 4;

        //писать результат сериализации будем во Writer(StringWriter)
        StringWriter writer = new StringWriter();

        //это объект Jackson, который выполняет сериализацию
        ObjectMapper mapper = new ObjectMapper();

        //сама сериализация: 1-куда, 2-что
        mapper.writeValue(writer, cat);

        //преобразовываем всё записанное во StringWriter в строку
        String result = writer.toString();
        System.out.println(result);
    }

    @JsonAutoDetect
    static class Cat {
        public String name;
        public int age;
        public int weight;
        Cat(){}
    }
}
