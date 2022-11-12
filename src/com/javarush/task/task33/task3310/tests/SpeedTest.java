package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest extends TestCase {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        long start = new Date().getTime();

        for (String string : strings)
            ids.add(shortener.getId(string));

        return (new Date().getTime() - start);
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        long start = new Date().getTime();

        for (Long id : ids)
            strings.add(shortener.getString(id));

        return (new Date().getTime() - start);
    }

    @Test
    public void testHashMapStorage() {
        // 1
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        // 2
        Set<String> origStrings = new HashSet<>(10_000);
        for (int i = 0; i < 10_000; i++)
            origStrings.add(Helper.generateRandomString());

        Set<Long> origIds = new HashSet<>(10_000);
        // 3
        long time_1, time_2;
        time_1 = getTimeToGetIds(shortener1, origStrings, origIds);
        time_2 = getTimeToGetIds(shortener2, origStrings, origIds);
        // 4
        assertTrue("Incorrect", time_1 > time_2);
        // 5

        for (long i = 0; i < 10_000; i++)
            origIds.add(i);

        time_1 = getTimeToGetStrings(shortener1, origIds, origStrings);
        time_2 = getTimeToGetStrings(shortener2, origIds, origStrings);
        // 6
        assertEquals(time_1, time_2, 30);
    }
}
