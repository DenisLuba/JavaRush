package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        MyLogParser logParser = new MyLogParser(Paths.get("C:\\Users\\support\\IdeaProjects\\JavaRush\\src\\com\\javarush\\task\\task39\\task3913\\logs"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, null));
        System.out.println("------------------------");
        logParser.getUniqueIPs(null, null).forEach(System.out::println);
        System.out.println("------------------------");
        logParser.getIPsForEvent(Event.SOLVE_TASK, null, null).forEach(System.out::println);
        System.out.println("------------------------");
        logParser.getIPsForUser("Amigo", null, null).forEach(System.out::println);
        System.out.println("------------------------");
        logParser.getIPsForStatus(Status.ERROR, null, null).forEach(System.out::println);
        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Amigo", new Date(), null));
        logParser.getDatesWhenUserDownloadedPlugin("Amigo", null, null).forEach(System.out::println);
        logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko", null, null).forEach(System.out::println);
    }
}
