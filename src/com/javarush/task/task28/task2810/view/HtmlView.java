package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";
//    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            String htmlFile = getUpdatedFileContent(vacancies);
            assert htmlFile != null;
            updateFile(htmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document document;
        try {
            document = getDocument();
            Element element = document
                    .getElementsByClass("template")
                    .first();
            Element template = element.clone();

            template.removeAttr("style");
            template.removeClass("template");

            document.select("tr[class=vacancy]").remove();

            for (Vacancy vacancy : vacancies) {
                Element copyOfTemplate = template.clone();
                copyOfTemplate
                        .getElementsByClass("city")
                        .first()
                        .text(vacancy.getCity());
                copyOfTemplate
                        .getElementsByClass("companyName")
                        .first()
                        .text(vacancy.getCompanyName());
                copyOfTemplate
                        .getElementsByClass("salary")
                        .first()
                        .text(vacancy.getSalary());
                Element title = copyOfTemplate
                        .getElementsByTag("a")
                        .first();
                title.attr("href", vacancy.getUrl());
                title.text(vacancy.getTitle());
                copyOfTemplate
                        .getElementsByClass("title")
                        .first()
                        .text(vacancy.getTitle());

                element.before(copyOfTemplate.outerHtml());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        System.out.println(document);
        return document.html();
    }
//
//    public static void main(String[] args) throws IOException {
//        Vacancy vacancy1 = new Vacancy();
//        vacancy1.setSiteName("Work");
//        vacancy1.setCity("New York");
//        vacancy1.setCompanyName("super company");
//        vacancy1.setTitle("Title of job");
//        vacancy1.setUrl("hh.ru/work");
//        vacancy1.setSalary("2000");
//        Vacancy vacancy2 = new Vacancy();
//        vacancy2.setSiteName("Work 2");
//        vacancy2.setCity("Moscow");
//        vacancy2.setCompanyName("super company 2");
//        vacancy2.setTitle("Title of job 2");
//        vacancy2.setUrl("hh.ru/work 2");
//        vacancy2.setSalary("1000");
//        List<Vacancy> vacancies = new ArrayList<>();
//        vacancies.add(vacancy1);
//        vacancies.add(vacancy2);
//        new HtmlView().getUpdatedFileContent(vacancies);
//    }

    private void updateFile(String htmlFile) throws IOException {
//        Files.write(Paths.get(filePath), htmlFile.getBytes());
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(htmlFile);
        }
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8", "hh.ru");
    }
}