package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.nio.file.Path;
import java.util.Date;
import java.util.Set;

public class LogParser implements IPQuery {
    Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }


    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return null;
    }
}
