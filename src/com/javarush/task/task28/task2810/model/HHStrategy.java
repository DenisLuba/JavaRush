package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

public class HHStrategy implements Strategy {
    private String URL_FORMAT = "https://grc.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        try {
            Jsoup.connect(String.format(URL_FORMAT, "NewYork", 1)).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
