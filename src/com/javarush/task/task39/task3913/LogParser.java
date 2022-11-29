//package com.javarush.task.task39.task3913;
//
//import com.javarush.task.task39.task3913.query.IPQuery;
//import com.javarush.task.task39.task3913.query.UserQuery;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.Set;
//import java.util.TreeSet;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class LogParser implements IPQuery, UserQuery {
//    private final Path logDirectory;
//
//    public LogParser(Path logDir) {
//        this.logDirectory = logDir;
//    }
//
//    @Override
//    public int getNumberOfUniqueIPs(Date after, Date before) {
//        return getUniqueIPs(after, before).size();
//    }
//
//    @Override
//    public Set<String> getUniqueIPs(Date after, Date before) {
//        return getLogs(after, before).stream().map(Log::getIP).collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getIPsForUser(String user, Date after, Date before) {
//        return getLogs(after, before).stream()
//                .filter(log -> user.equals(log.getNAME()))
//                .map(Log::getIP).collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
//        return getLogs(after, before).stream()
//                .filter(log -> log.getEVENT().equals(event))
//                .map(Log::getIP)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
//        return getLogs(after, before).stream()
//                .filter(log -> status.toString().equals(log.getSTATUS()))
//                .map(Log::getIP).collect(Collectors.toSet());
//    }
//--------------------------------------------------------------------------------------
//    @Override
//    public Set<String> getAllUsers() {
//        return getLogs(null, null).stream().map(Log::getNAME).collect(Collectors.toSet());
//    }
//
//    @Override
//    public int getNumberOfUsers(Date after, Date before) {
//        return getLogs(after, before).stream().map(Log::getNAME).collect(Collectors.toSet()).size();
//    }
//
//    @Override
//    public int getNumberOfUserEvents(String user, Date after, Date before) {
//        return getLogs(after, before).stream()
//                .filter(log -> log.getNAME().equals(user))
//                .map(Log::getEVENT)
//                .collect(Collectors.toSet()).size();
//    }
//
//    @Override
//    public Set<String> getUsersForIP(String ip, Date after, Date before) {
//        return getLogs(after, before).stream()
//                .filter(log -> log.getIP().equals(ip))
//                .map(Log::getNAME)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getLoggedUsers(Date after, Date before) {
//        return getLogs(after, before).stream()
//                .filter(log -> log.getEVENT().equals(Event.LOGIN))
//                .map(Log::getNAME)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
//        return getLogs(after, before).stream()
//                .filter(log -> log.getEVENT().equals(Event.DOWNLOAD_PLUGIN))
//                .map(Log::getNAME)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getWroteMessageUsers(Date after, Date before) {
//        return getLogs(after, before).stream()
//                .filter(log -> log.getEVENT().equals(Event.WRITE_MESSAGE))
//                .map(Log::getNAME)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getSolvedTaskUsers(Date after, Date before) {
//        return getLogs(after, before).stream()
//                .filter(log -> log.getEVENT().equals(Event.SOLVE_TASK))
//                .map(Log::getNAME)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
//        return getLogs(after, before).stream()
//                .filter(log -> log.getEVENT().equals(Event.SOLVE_TASK) && log.getTaskNumber() == task)
//                .map(Log::getNAME)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getDoneTaskUsers(Date after, Date before) {
//        return getLogs(after, before).stream()
//                .filter(log -> log.getEVENT().equals(Event.DONE_TASK))
//                .map(Log::getNAME)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
//        return getLogs(after, before).stream()
//                .filter(log -> log.getEVENT().equals(Event.DONE_TASK) && log.getTaskNumber() == task)
//                .map(Log::getNAME)
//                .collect(Collectors.toSet());
//    }
//
//    // метод getLogs(Date after, Date before) возвращает TreeSet логов,
//    // упорядоченный по дате, содержащий логи от и до дат after и before, соответственно и включительно.
//    private Set<Log> getLogs(Date after, Date before) {
//        Set<Log> set = null;
//        try (Stream<String> lines = Files.walk(logDirectory).collect(Collectors.toList())
//                .parallelStream()
//                .filter(file -> Files.isRegularFile(file) && file.toString().toLowerCase().endsWith(".log"))
//                .flatMap(LogParser::getLines)
//                .filter(string -> string.length() > 0)){
//
//            set = lines.map(Log::new)
//                    .filter(log -> (after == null || log.getDATE().after(after) || log.getDATE().equals(after))
//                            && (before == null || log.getDATE().before(before) || log.getDATE().equals(before)))
//                    .filter(log -> log.getDATE() != null)
//                    .collect(Collectors.toCollection(
//                            () -> new TreeSet<>(Comparator.comparing(Log::getDATE))));
//        } catch (IOException ignore) {}
//
//        return set;
//    }
//
//    // Метод вернет stream, содержащий все строки из файла
//    private static Stream<String> getLines(Path path) {
//        Stream<String> stream = null;
//        try {
//            stream = Files.lines(path, StandardCharsets.UTF_8);
//        } catch (IOException ignored) {}
//        return stream;
//    }
//
//    // Класс Log содержит поля IP, NAME, EVENT, STATUS, DATE и возвращает их значения в формате String
//    // (за исключением поля DATE: его формат - Date)
//    // Есть getters этих полей для удобства использования их в Stream API (например, так: Log::getDATE)
//
//    private static class Log {
//        private final String IP, NAME, STATUS;
//        private final Event EVENT;
//        private int taskNumber;
//        private final Date DATE;
//        private final String log;
//        private final String pattern = "^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(\\s+|\\t)" +
//                "(.+)(\\s+|\\t)" +
//                "(\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\s\\d{1,2}:\\d{1,2}:\\d{1,2})(\\s+|\\t)" +
//                "([A-Z_]+\\s?\\d*)(\\s+|\\t)" +
//                "([A-Z]+)$";
//
//        public Log(String log) {
//            this.log = log;
//            this.IP = log.replaceFirst(pattern, "$1");
//            this.NAME = log.replaceFirst(pattern, "$3").trim();
//            this.EVENT = getEventFromString(log);
//            this.STATUS = log.replaceFirst(pattern, "$9");
//            this.DATE = getDateFromString(log);
//        }
//
//        private Date getDateFromString(String string) {
//            String dateString = string.replaceFirst(pattern, "$5");
//            Date date = null;
//            try {
//                date = new SimpleDateFormat("d.M.yyyy H:m:s").parse(dateString);
//            } catch (ParseException e) {
//                e.getStackTrace();
//            }
//
//            return date;
//        }
//
//        private Event getEventFromString(String string) {
//            String event = string.replaceFirst(pattern, "$7");
//            String task = event.replaceFirst("[^0-9]+", "");
//            taskNumber = task.length() > 0 ? taskNumber = Integer.parseInt(task) : -1;
//
//            event = event.replaceFirst("[^A-Z_]+", "").trim();
//            return Event.valueOf(event);
//        }
//
//        public String getIP() {
//            return IP;
//        }
//
//        public String getNAME() {
//            return NAME;
//        }
//
//        public Date getDATE() {
//            return DATE;
//        }
//
//        public Event getEVENT() {
//            return EVENT;
//        }
//
//        public String getSTATUS() {
//            return STATUS;
//        }
//
//        public int getTaskNumber() {
//            return taskNumber;
//        }
//
//        @Override
//        public String toString() {
//            return log;
//        }
//    }
//}



package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
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

public class LogParser implements IPQuery, UserQuery, DateQuery {
    private final Path logDirectory;
    private Set<Log> logs;

    public LogParser(Path logDir) {
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
        return date.isEmpty() ? null : date.get();
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
        return date.isEmpty() ? null : date.get();
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
        return date.isEmpty() ? null : date.get();
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
        .flatMap(LogParser::getLines)){
            logs = lines.map(Log::new).collect(Collectors.toCollection(() -> new TreeSet<Log>(Comparator.comparing(Log::getDate))));
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
                date = new SimpleDateFormat("d.M.yyyy H:m:s").parse(string);
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