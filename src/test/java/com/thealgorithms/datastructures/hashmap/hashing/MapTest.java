package com.thealgorithms.datastructures.hashmap.hashing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import org.junit.jupiter.api.Test;

abstract class MapTest {
    abstract <Key extends Comparable<Key>, Value> Map<Key, Value> getMap();

    @Test
    void putTest() {
        Map<Integer, String> map = getMap();

        assertFalse(map.put(null, "-25"));
        assertFalse(map.put(null, null));
        assertTrue(map.put(-25, "-25"));
        assertTrue(map.put(33, "33"));
        assertTrue(map.put(100, "100"));
        assertTrue(map.put(100, "+100"));
        assertTrue(map.put(100, null));
    }

    @Test
    void getTest() {
        Map<Integer, String> map = getMap();
        for (int i = -100; i < 100; i++) {
            map.put(i, String.valueOf(i));
        }

        for (int i = -100; i < 100; i++) {
            assertEquals(map.get(i), String.valueOf(i));
        }

        for (int i = 100; i < 200; i++) {
            assertNull(map.get(i));
        }

        assertNull(map.get(null));
    }

    @Test
    void deleteTest() {
        Map<Integer, String> map = getMap();
        for (int i = -100; i < 100; i++) {
            map.put(i, String.valueOf(i));
        }

        for (int i = 0; i < 100; i++) {
            assertTrue(map.delete(i));
        }

        for (int i = 100; i < 200; i++) {
            assertFalse(map.delete(i));
        }

        assertFalse(map.delete(null));
    }

    @Test
    void containsTest() {
        Map<Integer, String> map = getMap();
        for (int i = -100; i < 100; i++) {
            map.put(i, String.valueOf(i));
        }

        for (int i = -50; i < 50; i++) {
            assertTrue(map.contains(i));
        }

        for (int i = 100; i < 200; i++) {
            assertFalse(map.contains(i));
        }

        assertFalse(map.contains(null));
    }

    @Test
    void sizeTest() {
        Map<Integer, String> map = getMap();
        assertEquals(map.size(), 0);

        for (int i = -100; i < 100; i++) {
            map.put(i, String.valueOf(i));
        }

        assertEquals(map.size(), 200);

        for (int i = -50; i < 50; i++) {
            map.delete(i);
        }

        assertEquals(map.size(), 100);
    }

    @Test
    void keysTest() {
        Map<Integer, String> map = getMap();
        Iterable<Integer> keys = map.keys();
        assertFalse(keys.iterator().hasNext());

        for (int i = 100; i > -100; i--) {
            map.put(i, String.valueOf(i));
        }

        keys = map.keys();
        int i = -100;
        for (Integer key : keys) {
            assertEquals(key, ++i);
        }
    }

    @Test
    void hashTest() {
        Map<Integer, String> map = getMap();
        int testSize = 100;
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomInt = random.nextInt();
            int hashIndex = map.hash(randomInt, testSize);
            int negateHashIndex = map.hash(-randomInt, testSize);
            assertTrue(hashIndex >= 0);
            assertTrue(hashIndex < testSize);
            assertTrue(negateHashIndex >= 0);
            assertTrue(negateHashIndex < testSize);
        }
    }
}
