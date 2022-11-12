package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest extends TestCase {

    private void testStorage(Shortener shortener) {
        String string_1 = Helper.generateRandomString();
        String string_2 = Helper.generateRandomString();
        String string_3 = string_1;

        Long id_1 = shortener.getId(string_1);
        Long id_2 = shortener.getId(string_2);
        Long id_3 = shortener.getId(string_3);

        Assert.assertNotEquals(id_2, id_1);
        Assert.assertNotEquals(id_2, id_3);
        Assert.assertEquals(id_1, id_3);

        String value_1 = shortener.getString(id_1);
        String value_2 = shortener.getString(id_2);
        String value_3 = shortener.getString(id_3);

        Assert.assertEquals(value_1, string_1);
        Assert.assertEquals(value_2, string_2);
        Assert.assertEquals(value_3, string_3);
    }

    @Test
    public void testHashMapStorageStrategy() {
        HashMapStorageStrategy strategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        OurHashMapStorageStrategy strategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        FileStorageStrategy strategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        HashBiMapStorageStrategy strategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        DualHashBidiMapStorageStrategy strategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        OurHashBiMapStorageStrategy strategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
}
