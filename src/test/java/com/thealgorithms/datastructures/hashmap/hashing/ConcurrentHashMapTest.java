package com.thealgorithms.datastructures.hashmap.hashing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConcurrentHashMapTest {

    @Test
    void testPutAndGet() {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>(10);
        map.put(1, "Value1");
        map.put(2, "Value2");
        map.put(3, "Value3");

        assertEquals("Value1", map.get(1));
        assertEquals("Value2", map.get(2));
        assertEquals("Value3", map.get(3));
        assertNull(map.get(4)); // Non-existent key
    }

    @Test
    void testUpdateValue() {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>(10);
        map.put(1, "Value1");
        map.put(1, "UpdatedValue1");

        assertEquals("UpdatedValue1", map.get(1)); // Verify updated value
    }

    @Test
    void testRemove() {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>(10);
        map.put(1, "Value1");
        map.put(2, "Value2");

        map.remove(1);
        assertNull(map.get(1)); // Verify removal
        assertEquals("Value2", map.get(2)); // Ensure other keys are unaffected
    }

    @Test
    void testContainsKey() {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>(10);
        map.put(1, "Value1");
        map.put(2, "Value2");

        assertTrue(map.containsKey(1));
        assertTrue(map.containsKey(2));
        assertFalse(map.containsKey(3)); // Non-existent key
    }

    @Test
    void testNullKey() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(10);
        map.put(null, "NullValue");

        assertEquals("NullValue", map.get(null)); // Verify null key handling
        map.remove(null);
        assertNull(map.get(null)); // Verify null key removal
    }

    @Test
    void testConcurrency() throws InterruptedException {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>(10);

        Thread writer1 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                map.put(i, i * 10);
            }
        });

        Thread writer2 = new Thread(() -> {
            for (int i = 50; i < 100; i++) {
                map.put(i, i * 10);
            }
        });

        Thread reader = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                map.get(i);
            }
        });

        writer1.start();
        writer2.start();
        reader.start();

        writer1.join();
        writer2.join();
        reader.join();

        for (int i = 0; i < 100; i++) {
            assertEquals(i * 10, map.get(i));
        }
    }

    @Test
    void testRemoveNonExistentKey() {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>(10);
        map.put(1, "Value1");
        map.remove(2); // Attempt to remove a non-existent key

        assertEquals("Value1", map.get(1)); // Ensure existing key remains
        assertNull(map.get(2)); // Confirm non-existent key remains null
    }

    @Test
    void testEmptyMap() {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>(10);
        assertNull(map.get(1)); // Test get on empty map
        assertFalse(map.containsKey(1)); // Test containsKey on empty map
    }

    @Test
    void testMultipleThreadsSameKey() throws InterruptedException {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>(10);

        Thread writer1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                map.put(1, i);
            }
        });

        Thread writer2 = new Thread(() -> {
            for (int i = 100; i < 200; i++) {
                map.put(1, i);
            }
        });

        writer1.start();
        writer2.start();

        writer1.join();
        writer2.join();

        assertNotNull(map.get(1)); // Ensure key exists
        assertTrue(map.get(1) >= 0 && map.get(1) < 200); // Value should be within range
    }
}