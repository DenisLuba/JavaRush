package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    public static void main(String[] args) throws ParseException {
        MyLogParser logParser = new MyLogParser(Paths.get("C:\\Users\\user\\IdeaProjects\\JavaRush\\src\\com\\javarush\\task\\task39\\task3913\\logs\\"));
        Date after = new SimpleDateFormat("d.M.yyyy H:m:s").parse("30.01.2014 12:56:22");
        Date before = new SimpleDateFormat("d.M.yyyy H:m:s").parse("19.03.2016 00:00:00");

//        System.out.println(logParser.getNumberOfUniqueIPs(null, null));
//        System.out.println("------------------------");
//        logParser.getUniqueIPs(null, null).forEach(System.out::println);
//        System.out.println("------------------------");
//        logParser.getIPsForEvent(Event.SOLVE_TASK, null, null).forEach(System.out::println);
//        System.out.println("------------------------");
//        logParser.getIPsForUser("amigo", null, null).forEach(System.out::println);
//        System.out.println("------------------------");
//        logParser.getIPsForStatus(Status.ERROR, null, null).forEach(System.out::println);
//        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Amigo", new Date(), null));
//        logParser.getDatesWhenUserDownloadedPlugin("Amigo", null, null).forEach(System.out::println);
//        logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko", null, null).forEach(System.out::println);
//
//        System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(18, null, null));
//        System.out.println("-----------------------------------------------------");
//        logParser.getAllSolvedTasksAndTheirNumber(after, before).forEach((key, value) -> System.out.println(key + ": " + value));
//        System.out.println("-----------------------------------------------------");
//        logParser.getAllDoneTasksAndTheirNumber(after, before).forEach((key, value) -> System.out.println(key + ": " + value));

        logParser.execute("get ip").forEach(System.out::println);
        System.out.println("**************************************");
        logParser.execute("get user").forEach(System.out::println);
        System.out.println("**************************************");
        logParser.execute("get date").forEach(System.out::println);
        System.out.println("**************************************");
        logParser.execute("get event").forEach(System.out::println);
        System.out.println("**************************************");
        logParser.execute("get status").forEach(System.out::println);
        System.out.println("**************************************");
//        logParser.execute("get tatus").forEach(System.out::println);

        logParser.execute("get ip for user = \"Vasya Pupkin\"").forEach(System.out::println);
        System.out.println("**************************************");
        logParser.execute("get event for date = \"30.01.2014 12:56:22\"").forEach(System.out::println);
        System.out.println("**************************************");
        logParser.execute("get ip for date = \"30.01.2014 12:56:22\"").forEach(System.out::println);

        System.out.println("**************************************");
        logParser.execute(("get ip for user = \"Amigo\" and date between \"14.10.2021 11:38:21\" and \"29.2.2028 5:4:7\"")).forEach(System.out::println);
    }
}
