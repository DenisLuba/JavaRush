package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.Provider;

import java.util.Arrays;
import java.util.stream.Stream;

public class Controller {
    private Provider[] providers;

    public Controller(Provider... providers) throws IllegalArgumentException {
        if (providers == null || providers.length == 0)
            throw new IllegalArgumentException();
        this.providers = providers;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }

    public void scan() {
        Stream.of(providers).flatMap(provider -> provider.getJavaVacancies())
    }
}
