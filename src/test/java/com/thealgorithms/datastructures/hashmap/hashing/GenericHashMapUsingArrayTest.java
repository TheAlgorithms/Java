package com.thealgorithms.datastructures.hashmap.hashing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GenericHashMapUsingArrayTest {

    @Test
    void testGenericHashmapWhichUsesArrayAndBothKeyAndValueAreStrings() {
        GenericHashMapUsingArray<String, String> map = new GenericHashMapUsingArray<>();
        map.put("USA", "Washington DC");
        map.put("Nepal", "Kathmandu");
        map.put("India", "New Delhi");
        map.put("Australia", "Sydney");
        assertNotNull(map);
        assertEquals(4, map.size());
        assertEquals("Kathmandu", map.get("Nepal"));
        assertEquals("Sydney", map.get("Australia"));
    }

    @Test
    void testGenericHashmapWhichUsesArrayAndKeyIsStringValueIsInteger() {
        GenericHashMapUsingArray<String, Integer> map = new GenericHashMapUsingArray<>();
        map.put("USA", 87);
        map.put("Nepal", 25);
        map.put("India", 101);
        map.put("Australia", 99);
        assertNotNull(map);
        assertEquals(4, map.size());
        assertEquals(25, map.get("Nepal"));
        assertEquals(99, map.get("Australia"));
        map.remove("Nepal");
        assertFalse(map.containsKey("Nepal"));
    }

    @Test
    void testGenericHashmapWhichUsesArrayAndKeyIsIntegerValueIsString() {
        GenericHashMapUsingArray<Integer, String> map = new GenericHashMapUsingArray<>();
        map.put(101, "Washington DC");
        map.put(34, "Kathmandu");
        map.put(46, "New Delhi");
        map.put(89, "Sydney");
        assertNotNull(map);
        assertEquals(4, map.size());
        assertEquals("Sydney", map.get(89));
        assertEquals("Washington DC", map.get(101));
        assertTrue(map.containsKey(46));
    }
}
