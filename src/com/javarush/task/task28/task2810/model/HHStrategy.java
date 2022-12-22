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
            Jsoup.connect(String.format(URL_FORMAT, "NewYork", 1))
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                    "Chrome/108.0.0.0 Safari/537.36")
                    .referrer("https://i.hh.ru/css/globals/pages/__globals_4c564e56d7fb5ca268e0ae004f4f2ba9.css")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
