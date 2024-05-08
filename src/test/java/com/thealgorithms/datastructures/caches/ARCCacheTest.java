package com.thealgorithms.datastructures.caches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ARCCacheTest {
    private ARCCache<Integer, String> cache;

    @BeforeEach
    public void setUp() {
        int t1Capacity = 2;
        int b1Capacity = 1;
        int totalCapacity = t1Capacity + b1Capacity;
        cache = new ARCCache<>(totalCapacity); // Set capacity to 3 for testing purposes
    }

    @Test
    public void testPutAndGet() {
        cache.put(1, "Value1");
        cache.put(2, "Value2");
        cache.put(3, "Value3");

        assertEquals("Value1", cache.get(1));
        assertEquals("Value2", cache.get(2));
        assertEquals("Value3", cache.get(3));
    }

    @Test
    public void testEviction() {
        cache.put(1, "Value1");
        cache.put(2, "Value2");
        cache.put(3, "Value3");

        cache.put(4, "Value4"); // This should evict key 1

        assertNull(cache.get(1)); // Key 1 should have been evicted
        assertEquals("Value2", cache.get(2)); // Other keys should still be present
        assertEquals("Value3", cache.get(3));
        assertEquals("Value4", cache.get(4));
    }

    @Test
    public void nullKeysAndValues() {
        cache.put(null, "Value1");
        cache.put(2, null);

        assertEquals("Value1", cache.get(null));
        assertNull(cache.get(2));
        assertNull(cache.get(6));
    }

    @Test
    public void testRepeatedGet() {
        cache.put(1, "Value1");

        // Repeated get calls should not affect eviction
        cache.get(1);
        cache.get(1);
        cache.get(1);

        // Adding new elements should still evict old ones
        cache.put(2, "Value2");
        cache.put(3, "Value3");
        cache.put(4, "Value4");

        assertNull(cache.get(2)); // Key 2 should have been evicted
        assertEquals("Value1", cache.get(1)); // Other keys should still be present
        assertEquals("Value3", cache.get(3)); // Other keys should still be present
    }
}
