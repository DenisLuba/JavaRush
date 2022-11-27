package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
TODO
1.1. Добавь в класс LogParser конструктор с параметром Path logDir, где logDir - директория с логами (логов может быть несколько, все они должны иметь расширение log).
1.2. Реализуй интерфейс IPQuery у класса LogParser:
1.2.1. Метод getNumberOfUniqueIPs(Date after, Date before) должен возвращать количество уникальных IP адресов за выбранный период.
Здесь и далее, если в методе есть параметры Date after и Date before, то нужно возвратить данные касающиеся только данного периода (включая даты after и before).
Если параметр after равен null, то нужно обработать все записи, у которых дата меньше или равна before.
Если параметр before равен null, то нужно обработать все записи, у которых дата больше или равна after.
Если и after, и before равны null, то нужно обработать абсолютно все записи (без фильтрации по дате).
1.2.2. Метод getUniqueIPs() должен возвращать множество, содержащее все не повторяющиеся IP. Тип в котором будем хранить IP будет String.
1.2.3. Метод getIPsForUser() должен возвращать IP, с которых работал переданный пользователь.
1.2.4. Метод getIPsForEvent() должен возвращать IP, с которых было произведено переданное событие.
1.2.5. Метод getIPsForStatus() должен возвращать IP, события с которых закончилось переданным статусом.
 */

public class LogParser implements IPQuery {
    private final Path logDirectory;

    public LogParser(Path logDir) {
        this.logDirectory = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getLogs(after, before).stream().map(Log::getIP).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getLogs(after, before).stream()
                .filter(log -> user.equals(log.getNAME()))
                .map(Log::getIP).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getLogs(after, before).stream()
                .filter(log -> log.getEVENT().startsWith(event.toString()))
                .map(Log::getIP)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getLogs(after, before).stream()
                .filter(log -> status.toString().equals(log.getSTATUS()))
                .map(Log::getIP).collect(Collectors.toSet());
    }


    // метод getLogs(Date after, Date before) возвращает TreeSet логов,
    // упорядоченный по дате, содержащий логи от и до дат after и before, соответственно и включительно.
    private Set<Log> getLogs(Date after, Date before) {
        Set<Log> set = null;
        try (Stream<String> lines = Files.lines(logDirectory, StandardCharsets.UTF_8)
                .filter(string -> string.length() > 0)){

            set = lines.map(Log::new)
                    .filter(log -> (after == null || log.getDATE().after(after) || log.getDATE().equals(after))
                            && (before == null || log.getDATE().before(before) || log.getDATE().equals(before)))
                    .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Log::getDATE))));
        } catch (IOException ignore) {}

        return set;
    }

    // Класс Log содержит поля IP, NAME, EVENT, STATUS, DATE и возвращает их значения в формате String
    // (за исключением поля DATE: его формат - Date)
    // Есть getters этих полей для удобства использования их в Stream API (например, так: Log::getDATE)

    private static class Log {
        private final String IP, NAME, EVENT, STATUS;
        private final Date DATE;
        private final String log;
        private final String pattern = "^(\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3})\\s" +
                "(.+)\\s" +
                "(\\d{1,2}.\\d{1,2}.\\d{4}\\s\\d{1,2}:\\d{1,2}:\\d{1,2})\\s" +
                "([A-Z_]+\\s?\\d*)\\s" +
                "([A-Z]+)";

        public Log(String log) {
            this.log = log;
            this.IP = getIpFromString(log);
            this.NAME = getNameFromString(log);
            this.EVENT = getEventFromString(log);
            this.STATUS = getStatusFromString(log);
            this.DATE = getDateFromString(log);
        }

        private String getIpFromString(String string) {
            String ip = string.replaceFirst(pattern, "($1)");
            return ip.substring(1, ip.length() - 1);
        }
        private String getNameFromString(String string) {
            String name = string.replaceFirst(pattern, "($2)");
            return name.substring(1, name.length() - 1);
        }
        private String getEventFromString(String string) {
            String event = string.replaceFirst(pattern, "($4)");
            return event.substring(1, event.length() - 1);
        }
        private String getStatusFromString(String string) {
            String status = string.replaceFirst(pattern, "($5)");
            return status.substring(1, status.length() - 1);
        }

        private Date getDateFromString(String string) {
            String dateString = string.replaceFirst(pattern, "($3)");
            dateString = dateString.substring(1, dateString.length() - 1);
            Date date = null;
            try {
                date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(dateString);
            } catch (ParseException e) {
                e.getStackTrace();
            }
            return date;
        }

        public String getIP() {
            return IP;
        }

        public String getNAME() {
            return NAME;
        }

        public Date getDATE() {
            return DATE;
        }

        public String getEVENT() {
            return EVENT;
        }

        public String getSTATUS() {
            return STATUS;
        }

        @Override
        public String toString() {
            return log;
        }
    }
}
