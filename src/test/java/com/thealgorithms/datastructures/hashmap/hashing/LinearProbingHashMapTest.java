package com.thealgorithms.datastructures.hashmap.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LinearProbingHashMapTest extends MapTest {

    @Override
    <Key extends Comparable<Key>, Value> Map<Key, Value> getMap() {
        return new LinearProbingHashMap<>();
    }

    @Test
    void putNullKey() {
        Map<Integer, String> map = getMap();
        assertFalse(map.put(null, "value"), "Putting a null key should return false");
    }

    @Test
    void putDuplicateKeys() {
        Map<Integer, String> map = getMap();
        map.put(1, "one");
        map.put(1, "uno");
        assertEquals("uno", map.get(1), "Value should be updated to 'uno'");
    }

    @Test
    void putResizeTest() {
        Map<Integer, String> map = getMap();
        for (int i = 0; i < 20; i++) {
            map.put(i, String.valueOf(i));
        }
        assertEquals(20, map.size(), "Map size should be 20 after inserting 20 elements");
    }

    @Test
    void deleteNonExistentKey() {
        Map<Integer, String> map = getMap();
        assertFalse(map.delete(999), "Deleting a non-existent key should return false");
    }

    @Test
    void deleteAndReinsert() {
        Map<Integer, String> map = getMap();
        map.put(1, "one");
        map.delete(1);
        assertFalse(map.contains(1), "Map should not contain the deleted key");
        map.put(1, "one again");
        assertTrue(map.contains(1), "Map should contain the key after reinsertion");
    }

    @Test
    void resizeDown() {
        Map<Integer, String> map = getMap();
        for (int i = 0; i < 16; i++) {
            map.put(i, String.valueOf(i));
        }
        for (int i = 0; i < 12; i++) {
            map.delete(i);
        }
        assertEquals(4, map.size(), "Map size should be 4 after deleting 12 elements");
    }

    @Test
    void keysOrderTest() {
        Map<Integer, String> map = getMap();
        for (int i = 10; i > 0; i--) {
            map.put(i, String.valueOf(i));
        }
        int expectedKey = 1;
        for (Integer key : map.keys()) {
            assertEquals(expectedKey++, key, "Keys should be in sorted order");
        }
    }

    @Test
    void stressTest() {
        Map<Integer, String> map = getMap();
        for (int i = 0; i < 1000; i++) {
            map.put(i, String.valueOf(i));
            assertEquals(i + 1, map.size(), "Size should match number of inserted elements");
        }
        for (int i = 0; i < 500; i++) {
            map.delete(i);
            assertEquals(1000 - (i + 1), map.size(), "Size should decrease correctly");
        }
    }
}
