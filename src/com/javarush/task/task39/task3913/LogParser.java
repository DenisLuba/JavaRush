package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
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

public class LogParser implements IPQuery, UserQuery, DateQuery {
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

    @Override
    public Set<String> getAllUsers() {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            result.add(logEntities.get(i).getUser());
        }
        return result;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                result.add(logEntities.get(i).getUser());
            }
        }
        return result.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)) {
                    result.add(logEntities.get(i).getEvent());
                }
            }
        }
        return result.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getIp().equals(ip)) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.LOGIN)) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.DOWNLOAD_PLUGIN)) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.WRITE_MESSAGE)) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.SOLVE_TASK)) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.SOLVE_TASK)
                        && logEntities.get(i).getEventAdditionalParameter() == task) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.DONE_TASK)) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.DONE_TASK)
                        && logEntities.get(i).getEventAdditionalParameter() == task) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)
                        && logEntities.get(i).getEvent().equals(event)) {
                    result.add(logEntities.get(i).getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getStatus().equals(Status.FAILED)) {
                    result.add(logEntities.get(i).getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getStatus().equals(Status.ERROR)) {
                    result.add(logEntities.get(i).getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)
                        && logEntities.get(i).getEvent().equals(Event.LOGIN)) {
                    set.add(logEntities.get(i).getDate());
                }
            }
        }
        if (set.size() == 0) {
            return null;
        }
        Date minDate = set.iterator().next();
        for (Date date : set) {
            if (date.getTime() < minDate.getTime())
                minDate = date;
        }
        return minDate;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)
                        && logEntities.get(i).getEvent().equals(Event.SOLVE_TASK)
                        && logEntities.get(i).getEventAdditionalParameter() == task) {
                    set.add(logEntities.get(i).getDate());
                }
            }
        }
        if (set.size() == 0) {
            return null;
        }
        Date minDate = set.iterator().next();
        for (Date date : set) {
            if (date.getTime() < minDate.getTime())
                minDate = date;
        }
        return minDate;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)
                        && logEntities.get(i).getEvent().equals(Event.DONE_TASK)
                        && logEntities.get(i).getEventAdditionalParameter() == task) {
                    set.add(logEntities.get(i).getDate());
                }
            }
        }
        if (set.size() == 0) {
            return null;
        }
        Date minDate = set.iterator().next();
        for (Date date : set) {
            if (date.getTime() < minDate.getTime())
                minDate = date;
        }
        return minDate;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)
                        && logEntities.get(i).getEvent().equals(Event.WRITE_MESSAGE)) {
                    result.add(logEntities.get(i).getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)
                        && logEntities.get(i).getEvent().equals(Event.DOWNLOAD_PLUGIN)) {
                    result.add(logEntities.get(i).getDate());
                }
            }
        }
        return result;
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

        public Log()

        public Log(String line) {
            String[] parameters = line.split("\t");

//            String[] parameters = getArrayOfParameters(line);
            if (parameters.length != 5) return;

            ip = parameters[0];
            user = parameters[1];
            setDateFromString(parameters[2]);
            setEventAndTaskFromString(parameters[3]);
            status = Status.valueOf(parameters[4]);
        }

//        ---------------------------------------------------------
//        private String[] getArrayOfParameters(String log) {
//            String pattern = "^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(\\s+|\\t)" +
//                "(.+)(\\s+|\\t)" +
//                "(\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\s\\d{1,2}:\\d{1,2}:\\d{1,2})(\\s+|\\t)" +
//                "([A-Z_]+\\s?\\d*)(\\s+|\\t)" +
//                "([A-Z]+)$";
//
//            String[] parameters = new String[5];
//
//            parameters[0] = log.replaceFirst(pattern, "$1");
//            parameters[1] = log.replaceFirst(pattern, "$3").trim();
//            parameters[2] = log.replaceFirst(pattern, "$5");
//            parameters[3] = log.replaceFirst(pattern, "$7");
//            parameters[4] = log.replaceFirst(pattern, "$9");
//
//            return parameters;
//        }

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

    private void readLogs() {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(logDirectory)) {
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

                            Log logEntity = new Log(ip, user, date, event, eventAdditionalParameter, status);
                            logs.add(logEntity);
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
}