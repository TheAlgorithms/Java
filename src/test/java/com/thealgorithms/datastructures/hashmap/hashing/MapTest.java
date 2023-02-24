package com.thealgorithms.datastructures.hashmap.hashing;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

abstract class MapTest {
    abstract <Key extends Comparable<Key>, Value> Map<Key, Value> getMap();

    @Test
    void mapFunctionsTest() {
        Map<Integer, String> map = getMap();
        for (int i = 0; i < 100; i++) {
            map.put(i, String.valueOf(i));
        }
        map.put(null, null);

        for (int i = 0; i < 100; i++) {
            assertEquals(map.get(i), String.valueOf(i));
        }

        assertEquals(map.size(), 100);


        for (int i = 40; i < 100; i++) {
            map.delete(i);
        }

        assertEquals(map.size(), 40);

        assertTrue(map.delete(39));
        assertFalse(map.delete(90));

        Iterable<Integer> keys = map.keys();
        ArrayList<Integer> expectedKeys = new ArrayList<>();
        for (int i = 0; i < 39; i++) {
            expectedKeys.add(i);
        }

        assertIterableEquals(keys, expectedKeys);

        assertTrue(map.contains(10));
        assertFalse(map.contains(100));
    }
}
