package com.thealgorithms.datastructures.caches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class MRUCacheTest {

    private static final int SIZE = 5;

    @Test
    public void putAndGetIntegerValues() {
        MRUCache<Integer, Integer> lruCache = new MRUCache<>(SIZE);

        for (int i = 0; i < SIZE; i++) {
            lruCache.put(i, i);
        }

        for (int i = 0; i < SIZE; i++) {
            assertEquals(i, lruCache.get(i));
        }
    }

    @Test
    public void putAndGetStringValues() {
        MRUCache<String, String> lruCache = new MRUCache<>(SIZE);

        for (int i = 0; i < SIZE; i++) {
            lruCache.put("key" + i, "value" + i);
        }

        for (int i = 0; i < SIZE; i++) {
            assertEquals("value" + i, lruCache.get("key" + i));
        }
    }

    @Test
    public void nullKeysAndValues() {
        MRUCache<Integer, Integer> mruCache = new MRUCache<>(SIZE);
        mruCache.put(null, 2);
        mruCache.put(6, null);

        assertEquals(2, mruCache.get(null));
        assertNull(mruCache.get(6));
    }

    @Test
    public void overCapacity() {
        MRUCache<Integer, Integer> mruCache = new MRUCache<>(SIZE);

        for (int i = 0; i < 10; i++) {
            mruCache.put(i, i);
        }

        assertEquals(9, mruCache.get(9));
    }
}
