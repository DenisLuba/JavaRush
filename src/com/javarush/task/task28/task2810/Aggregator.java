package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.model.Strategy;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Aggregator {
    public static void main(String[] args) {
        Provider provider = new Provider(new HHStrategy());
        Controller controller = new Controller(provider);
        controller.scan();

//        Provider[] providers = new Provider[3];
//        providers[0] = new Provider(searchString -> Collections.singletonList(new Vacancy()));
//        providers[1] = new Provider(searchString -> Arrays.asList(new Vacancy(), new Vacancy()));
//        providers[2] = new Provider(searchString -> Arrays.asList(new Vacancy(), new Vacancy(), new Vacancy()));
//
//        new Controller(providers).scan();
    }
}
