package com.thealgorithms.datastructures.hashmap.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ImmutableHashMapTest {

    @Test
    void testEmptyMap() {
        ImmutableHashMap<String, Integer> map = ImmutableHashMap.<String, Integer>empty();

        assertEquals(0, map.size());
        assertNull(map.get("A"));
    }

    @Test
    void testPutDoesNotModifyOriginalMap() {
        ImmutableHashMap<String, Integer> map1 = ImmutableHashMap.<String, Integer>empty();

        ImmutableHashMap<String, Integer> map2 = map1.put("A", 1);

        assertEquals(0, map1.size());
        assertEquals(1, map2.size());
        assertNull(map1.get("A"));
        assertEquals(1, map2.get("A"));
    }

    @Test
    void testMultiplePuts() {
        ImmutableHashMap<String, Integer> map = ImmutableHashMap.<String, Integer>empty().put("A", 1).put("B", 2);

        assertEquals(2, map.size());
        assertEquals(1, map.get("A"));
        assertEquals(2, map.get("B"));
    }

    @Test
    void testContainsKey() {
        ImmutableHashMap<String, Integer> map = ImmutableHashMap.<String, Integer>empty().put("X", 100);

        assertTrue(map.containsKey("X"));
        assertFalse(map.containsKey("Y"));
    }

    @Test
    void testNullKey() {
        ImmutableHashMap<String, Integer> map = ImmutableHashMap.<String, Integer>empty().put(null, 50);

        assertEquals(50, map.get(null));
    }
}
