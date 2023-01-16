package com.javarush.task.task39.task3911;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Software {
    private int currentVersion;

    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    public boolean rollback(int rollbackVersion) {
        if (!versionHistoryMap.containsKey(rollbackVersion)) return false;
        versionHistoryMap = versionHistoryMap.entrySet().stream()
                .filter(entry -> entry.getKey() <= rollbackVersion)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

//        Set<Integer> set = versionHistoryMap.keySet().stream()
//                .filter(key -> key <= rollbackVersion)
//                .collect(Collectors.toSet());
//        versionHistoryMap.keySet().retainAll(set);

        currentVersion = rollbackVersion;
        return true;
    }
}
