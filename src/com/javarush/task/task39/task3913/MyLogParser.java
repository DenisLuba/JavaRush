package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.EventQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyLogParser implements IPQuery, UserQuery, DateQuery, EventQuery {
    private final Path logDirectory;
    private Set<Log> logs;
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d.M.yyyy H:m:s");

    public MyLogParser(Path logDir) {
        this.logDirectory = logDir;
        readLogs();
    }

//                               IPQuery

//    **************************************************************

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before))
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before) && log.user.equals(user))
                .map(Log::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before) && log.event.equals(event))
                .map(Log::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before) && log.status.equals(status))
                .map(Log::getIp)
                .collect(Collectors.toSet());
    }

//                               UserQuery

//    **************************************************************

    @Override
    public Set<String> getAllUsers() {
        return logs.stream()
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before))
                .map(Log::getUser)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return logs.stream()
                .filter(log -> log.user.equals(user) && isRelevantDate(log.date, after, before))
                .map(Log::getUser)
                .collect(Collectors.toSet()).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return logs.stream()
                .filter(log -> log.ip.equals(ip) && isRelevantDate(log.date, after, before))
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.event.equals(Event.LOGIN) && isRelevantDate(log.date, after, before))
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.event.equals(Event.DOWNLOAD_PLUGIN) && isRelevantDate(log.date, after, before))
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.event.equals(Event.WRITE_MESSAGE) && isRelevantDate(log.date, after, before))
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.event.equals(Event.SOLVE_TASK) && isRelevantDate(log.date, after, before))
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return logs.stream()
                .filter(log -> log.event.equals(Event.SOLVE_TASK)
                        && log.taskNumber == task
                        && isRelevantDate(log.date, after, before))
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return logs.stream()
                .filter(log -> log.event.equals(Event.DONE_TASK) && isRelevantDate(log.date, after, before))
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return logs.stream()
                .filter(log -> log.event.equals(Event.DONE_TASK)
                        && log.taskNumber == task
                        && isRelevantDate(log.date, after, before))
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

//                               DateQuery

//    **************************************************************

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before)
                        && log.event.equals(event)
                        && log.user.equals(user))
                .map(Log::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before)
                        && log.status.equals(Status.FAILED))
                .map(Log::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before)
                        && log.status.equals(Status.ERROR))
                .map(Log::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Optional<Date> date= logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before)
                        && log.user.equals(user)
                        && log.event.equals(Event.LOGIN))
                .map(Log::getDate)
                .findFirst();
        return date.orElse(null);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Optional<Date> date = logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before)
                        && log.user.equals(user)
                        && log.taskNumber == task
                        && log.event.equals(Event.SOLVE_TASK))
                .map(Log::getDate)
                .findFirst();
        return date.orElse(null);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Optional<Date> date = logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before)
                        && log.user.equals(user)
                        && log.taskNumber == task
                        && log.event.equals(Event.DONE_TASK))
                .map(Log::getDate)
                .findFirst();
        return date.orElse(null);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before)
                        && log.user.equals(user)
                        && log.event.equals(Event.WRITE_MESSAGE))
                .map(Log::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before)
                        && log.user.equals(user)
                        && log.event.equals(Event.DOWNLOAD_PLUGIN))
                .map(Log::getDate)
                .collect(Collectors.toSet());
    }

//                               EventQuery

//    **************************************************************

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return (int) logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before))
                .count();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before))
                .map(Log::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before)
                        && log.ip.equals(ip))
                .map(Log::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before)
                        && log.user.equals(user))
                .map(Log::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before)
                        && log.status.equals(Status.FAILED))
                .map(Log::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before)
                        && log.status.equals(Status.ERROR))
                .map(Log::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before)
                        && log.event.equals(Event.SOLVE_TASK)
                        && log.taskNumber == task)
                .count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int) logs.stream()
                .filter(log -> isRelevantDate(log.date, after, before)
                        && log.event.equals(Event.DONE_TASK)
                        && log.taskNumber == task
                        && log.status.equals(Status.OK))
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return logs.stream()
                .collect(Collectors.toMap(log -> log.taskNumber,
                        log -> this.getNumberOfAttemptToSolveTask(log.taskNumber, after, before)));
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return logs.stream()
                .map(Log::getTaskNumber)
                .distinct()
                .collect(Collectors.toMap(task -> task, task -> this.getNumberOfSuccessfulAttemptToSolveTask(task, after, before)));
    }

//    **************************************************************

    private static Stream<String> getLines(Path path) {
        Stream<String> lines = null;
        try {
            lines = Files.lines(path);
        } catch (IOException ignore) {}
        return lines;
    }


    private void readLogs() {
        try (Stream<String> lines = Files.walk(logDirectory).filter(file -> file.toString().toLowerCase().endsWith(".log"))
        .flatMap(MyLogParser::getLines)){
            logs = lines.map(Log::new).collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Log::getDate))));
        } catch (IOException ignore) {}
    }

    private boolean isRelevantDate(Date current, Date after, Date before) {
        if (after == null) {
            after = new Date(0);
        }
        if (before == null) {
            before = new Date(Long.MAX_VALUE);
        }
        return current.after(after) && current.before(before);
    }

//                               Inner class Log

//    **************************************************************

    private static class Log {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private int taskNumber;
        private Status status;

        public Log(String line) {
//            String[] parameters = line.split("\t");

            String[] parameters = getArrayOfParameters(line);
            if (parameters.length != 5) return;

            ip = parameters[0];
            user = parameters[1];
            setDateFromString(parameters[2]);
            setEventAndTaskFromString(parameters[3]);
            status = Status.valueOf(parameters[4]);
        }

//        ---------------------------------------------------------
        private String[] getArrayOfParameters(String log) {
            String pattern = "^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(\\s+|\\t)" +
                "(.+)(\\s+|\\t)" +
                "(\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\s\\d{1,2}:\\d{1,2}:\\d{1,2})(\\s+|\\t)" +
                "([A-Z_]+\\s?\\d*)(\\s+|\\t)" +
                "([A-Z]+)$";

            String[] parameters = new String[5];

            parameters[0] = log.replaceFirst(pattern, "$1");
            parameters[1] = log.replaceFirst(pattern, "$3").trim();
            parameters[2] = log.replaceFirst(pattern, "$5");
            parameters[3] = log.replaceFirst(pattern, "$7");
            parameters[4] = log.replaceFirst(pattern, "$9");

            return parameters;
        }

//        -------------------------------------------------------------

        private void setDateFromString(String string) {
            try {
                date = simpleDateFormat.parse(string);
            } catch (ParseException ignore) {}
        }

        private void setEventAndTaskFromString(String string) {
            event = Event.valueOf(string.replaceAll("[^A-Z_]+", "").trim());
            String number = string.replaceAll("[^0-9]+", "");
            taskNumber = number.length() > 0 ? Integer.parseInt(number) : -1;
        }

        public String getIp() {
            return ip;
        }

        public String getUser() {
            return user;
        }

        public Date getDate() {
            return date;
        }

        public Event getEvent() {
            return event;
        }

        public int getTaskNumber() {
            return taskNumber;
        }

        public Status getStatus() {
            return status;
        }
    }
}