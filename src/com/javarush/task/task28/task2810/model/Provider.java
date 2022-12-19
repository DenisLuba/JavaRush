package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.List;

// this class is a class "Context"
public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    // this method is a method "execute"
    public List<Vacancy> getJavaVacancies(String searchString) {
        return null;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
