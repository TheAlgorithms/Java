package com.thealgorithms.datastructures.caches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class LFUCacheTest {

    @Test
    void testLFUCacheWithIntegerValueShouldPass() {
        LFUCache<Integer, Integer> lfuCache = new LFUCache<>(5);
        lfuCache.put(1, 10);
        lfuCache.put(2, 20);
        lfuCache.put(3, 30);
        lfuCache.put(4, 40);
        lfuCache.put(5, 50);

        // get method call will increase frequency of key 1 by 1
        assertEquals(10, lfuCache.get(1));

        // this operation will remove value with key as 2
        lfuCache.put(6, 60);

        // will return null as value with key 2 is now evicted
        assertNull(lfuCache.get(2));

        // should return 60
        assertEquals(60, lfuCache.get(6));

        // this operation will remove value with key as 3
        lfuCache.put(7, 70);

        assertNull(lfuCache.get(2));
        assertEquals(70, lfuCache.get(7));
    }

    @Test
    void testLFUCacheWithStringValueShouldPass() {
        LFUCache<Integer, String> lfuCache = new LFUCache<>(5);
        lfuCache.put(1, "Alpha");
        lfuCache.put(2, "Beta");
        lfuCache.put(3, "Gamma");
        lfuCache.put(4, "Delta");
        lfuCache.put(5, "Epsilon");

        // get method call will increase frequency of key 1 by 1
        assertEquals("Alpha", lfuCache.get(1));

        // this operation will remove value with key as 2
        lfuCache.put(6, "Digamma");

        // will return null as value with key 2 is now evicted
        assertNull(lfuCache.get(2));

        // should return string Digamma
        assertEquals("Digamma", lfuCache.get(6));

        // this operation will remove value with key as 3
        lfuCache.put(7, "Zeta");

        assertNull(lfuCache.get(2));
        assertEquals("Zeta", lfuCache.get(7));
    }

    @Test
    void testUpdateValueShouldPreserveFrequency() {
        LFUCache<Integer, String> lfuCache = new LFUCache<>(3);
        lfuCache.put(1, "A");
        lfuCache.put(2, "B");
        lfuCache.put(3, "C");

        assertEquals("A", lfuCache.get(1)); // Accessing key 1
        lfuCache.put(4, "D"); // This should evict key 2

        assertNull(lfuCache.get(2)); // Key 2 should be evicted
        assertEquals("C", lfuCache.get(3)); // Key 3 should still exist
        assertEquals("A", lfuCache.get(1)); // Key 1 should still exist

        lfuCache.put(1, "Updated A"); // Update the value of key 1
        assertEquals("Updated A", lfuCache.get(1)); // Check if the update was successful
    }

    @Test
    void testEvictionPolicyWhenFull() {
        LFUCache<Integer, String> lfuCache = new LFUCache<>(2);
        lfuCache.put(1, "One");
        lfuCache.put(2, "Two");

        assertEquals("One", lfuCache.get(1)); // Access key 1
        lfuCache.put(3, "Three"); // This should evict key 2 (least frequently used)

        assertNull(lfuCache.get(2)); // Key 2 should be evicted
        assertEquals("One", lfuCache.get(1)); // Key 1 should still exist
        assertEquals("Three", lfuCache.get(3)); // Check if key 3 exists
    }

    @Test
    void testGetFromEmptyCacheShouldReturnNull() {
        LFUCache<Integer, String> lfuCache = new LFUCache<>(3);
        assertNull(lfuCache.get(1)); // Should return null as the cache is empty
    }

    @Test
    void testPutNullValueShouldStoreNull() {
        LFUCache<Integer, String> lfuCache = new LFUCache<>(3);
        lfuCache.put(1, null); // Store a null value

        assertNull(lfuCache.get(1)); // Should return null
    }

    @Test
    void testInvalidCacheCapacityShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new LFUCache<>(0));
        assertThrows(IllegalArgumentException.class, () -> new LFUCache<>(-1));
    }

    @Test
    void testMultipleAccessPatterns() {
        LFUCache<Integer, String> lfuCache = new LFUCache<>(5);
        lfuCache.put(1, "A");
        lfuCache.put(2, "B");
        lfuCache.put(3, "C");
        lfuCache.put(4, "D");

        assertEquals("A", lfuCache.get(1)); // Access 1
        lfuCache.put(5, "E"); // Should not evict anything yet
        lfuCache.put(6, "F"); // Evict B

        assertNull(lfuCache.get(2)); // B should be evicted
        assertEquals("C", lfuCache.get(3)); // C should still exist
        assertEquals("D", lfuCache.get(4)); // D should still exist
        assertEquals("A", lfuCache.get(1)); // A should still exist
        assertEquals("E", lfuCache.get(5)); // E should exist
        assertEquals("F", lfuCache.get(6)); // F should exist
    }
}
