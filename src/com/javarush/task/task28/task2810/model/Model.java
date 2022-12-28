package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.view.View;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers) throws IllegalArgumentException {
        if (view == null || providers == null || providers.length == 0)
            throw new IllegalArgumentException();
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city) {
        view.update(Stream.of(providers)
                .flatMap(provider -> provider.getJavaVacancies(city).stream())
                .collect(Collectors.toList()));
    }
}
