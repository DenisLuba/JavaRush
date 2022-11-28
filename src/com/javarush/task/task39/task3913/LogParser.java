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

import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery {
    private Path logDir;
    private List<LogEntity> logEntities = new ArrayList<>();
    private DateFormat simpleDateFormat = new SimpleDateFormat("d.M.yyyy H:m:s");

    public LogParser(Path logDir) {
        this.logDir = logDir;
        readLogs();
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                result.add(logEntities.get(i).getIp());
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)) {
                    result.add(logEntities.get(i).getIp());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(event)) {
                    result.add(logEntities.get(i).getIp());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getStatus().equals(status)) {
                    result.add(logEntities.get(i).getIp());
                }
            }
        }
        return result;
    }

//-------------------------------------------------------------------------------------------------------

    @Override
    public Set<String> getAllUsers() {
        return logEntities.stream().map(log -> log.user).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return logEntities.stream()
                .filter(log -> dateBetweenDates(log.date, after, before))
                .map(log -> log.user)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return logEntities.stream()
                .filter(log -> log.user.equals(user) && dateBetweenDates(log.date, after, before))
                .map(log -> log.event)
                .collect(Collectors.toSet()).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return logEntities.stream()
                .filter(log -> log.ip.equals(ip) && dateBetweenDates(log.date, after, before))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return logEntities.stream()
                .filter(log -> log.event.equals(Event.LOGIN) && dateBetweenDates(log.date, after, before))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return logEntities.stream()
                .filter(log -> log.event.equals(Event.DOWNLOAD_PLUGIN) && dateBetweenDates(log.date, after, before))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return logEntities.stream()
                .filter(log -> log.event.equals(Event.WRITE_MESSAGE) && dateBetweenDates(log.date, after, before))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return logEntities.stream()
                .filter(log -> log.event.equals(Event.SOLVE_TASK) && dateBetweenDates(log.date, after, before))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return logEntities.stream()
                .filter(log -> log.event.equals(Event.SOLVE_TASK)
                        && log.eventAdditionalParameter == task
                        && dateBetweenDates(log.date, after, before))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return logEntities.stream()
                .filter(log -> log.event.equals(Event.DONE_TASK) && dateBetweenDates(log.date, after, before))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return logEntities.stream()
                .filter(log -> log.event.equals(Event.DONE_TASK)
                        && log.eventAdditionalParameter == task
                        && dateBetweenDates(log.date, after, before))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    private void readLogs() {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(logDir)) {
            for (Path file : directoryStream) {
                if (file.toString().toLowerCase().endsWith(".log")) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            String[] params = line.split("\t");

                            if (params.length != 5) {
                                continue;
                            }

                            String ip = params[0];
                            String user = params[1];
                            Date date = readDate(params[2]);
                            Event event = readEvent(params[3]);
                            int eventAdditionalParameter = -1;
                            if (event.equals(Event.SOLVE_TASK) || event.equals(Event.DONE_TASK)) {
                                eventAdditionalParameter = readAdditionalParameter(params[3]);
                            }
                            Status status = readStatus(params[4]);

                            LogEntity logEntity = new LogEntity(ip, user, date, event, eventAdditionalParameter, status);
                            logEntities.add(logEntity);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Date readDate(String lineToParse) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(lineToParse);
        } catch (ParseException e) {
        }
        return date;
    }

    private Event readEvent(String lineToParse) {
        Event event = null;
        if (lineToParse.contains("SOLVE_TASK")) {
            event = Event.SOLVE_TASK;
        } else if (lineToParse.contains("DONE_TASK")) {
            event = Event.DONE_TASK;
        } else {
            switch (lineToParse) {
                case "LOGIN": {
                    event = Event.LOGIN;
                    break;
                }
                case "DOWNLOAD_PLUGIN": {
                    event = Event.DOWNLOAD_PLUGIN;
                    break;
                }
                case "WRITE_MESSAGE": {
                    event = Event.WRITE_MESSAGE;
                    break;
                }
            }
        }
        return event;
    }

    private int readAdditionalParameter(String lineToParse) {
        if (lineToParse.contains("SOLVE_TASK")) {
            lineToParse = lineToParse.replace("SOLVE_TASK", "").replaceAll(" ", "");
            return Integer.parseInt(lineToParse);
        } else {
            lineToParse = lineToParse.replace("DONE_TASK", "").replaceAll(" ", "");
            return Integer.parseInt(lineToParse);
        }
    }

    private Status readStatus(String lineToParse) {
        Status status = null;
        switch (lineToParse) {
            case "OK": {
                status = Status.OK;
                break;
            }
            case "FAILED": {
                status = Status.FAILED;
                break;
            }
            case "ERROR": {
                status = Status.ERROR;
                break;
            }
        }
        return status;
    }

    private boolean dateBetweenDates(Date current, Date after, Date before) {
        if (after == null) {
            after = new Date(0);
        }
        if (before == null) {
            before = new Date(Long.MAX_VALUE);
        }
        return current.after(after) && current.before(before);
    }

    private class LogEntity {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private int eventAdditionalParameter;
        private Status status;

        public LogEntity(String ip, String user, Date date, Event event, int eventAdditionalParameter, Status status) {
            this.ip = ip;
            this.user = user;
            this.date = date;
            this.event = event;
            this.eventAdditionalParameter = eventAdditionalParameter;
            this.status = status;
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

        public int getEventAdditionalParameter() {
            return eventAdditionalParameter;
        }

        public Status getStatus() {
            return status;
        }
    }
}