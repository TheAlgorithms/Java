package com.thealgorithms.datastructures.caches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class MRUCacheTest {

    private static final int SIZE = 5;

    @Test
    public void putAndGetIntegerValues() {
        MRUCache<Integer, Integer> mruCache = new MRUCache<>(SIZE);

        for (int i = 0; i < SIZE; i++) {
            mruCache.put(i, i);
        }

        for (int i = 0; i < SIZE; i++) {
            assertEquals(i, mruCache.get(i));
        }
    }

    @Test
    public void putAndGetStringValues() {
        MRUCache<String, String> mruCache = new MRUCache<>(SIZE);

        for (int i = 0; i < SIZE; i++) {
            mruCache.put("key" + i, "value" + i);
        }

        for (int i = 0; i < SIZE; i++) {
            assertEquals("value" + i, mruCache.get("key" + i));
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

        // After inserting 10 items, the cache should have evicted the least recently used ones.
        assertEquals(9, mruCache.get(9)); // Most recently used
        assertEquals(0, mruCache.get(0)); // Least recently used, should be evicted
    }

    @Test
    public void overwriteExistingKey() {
        MRUCache<Integer, String> mruCache = new MRUCache<>(SIZE);
        mruCache.put(1, "one");
        mruCache.put(1, "uno"); // Overwriting the value for key 1

        assertEquals("uno", mruCache.get(1));
        assertNull(mruCache.get(2)); // Ensure other keys are unaffected
    }

    @Test
    public void evictionOrder() {
        MRUCache<Integer, Integer> mruCache = new MRUCache<>(SIZE);

        for (int i = 0; i < SIZE; i++) {
            mruCache.put(i, i);
        }

        // Access a key to make it most recently used
        mruCache.get(2);

        // Add new items to trigger eviction
        mruCache.put(5, 5);
        mruCache.put(6, 6);

        // Key 3 should be evicted since 2 is the most recently used
        assertEquals(3, mruCache.get(3));
        assertEquals(4, mruCache.get(4)); // Key 4 should still be available
        assertEquals(6, mruCache.get(6)); // Key 6 should be available
    }

    @Test
    public void cacheHandlesLargeValues() {
        MRUCache<String, String> mruCache = new MRUCache<>(SIZE);

        for (int i = 0; i < SIZE; i++) {
            mruCache.put("key" + i, "value" + i);
        }

        // Verify values
        for (int i = 0; i < SIZE; i++) {
            assertEquals("value" + i, mruCache.get("key" + i));
        }

        // Add large value
        mruCache.put("largeKey", "largeValue");

        // Verify eviction of the least recently used (key 0 should be evicted)
        assertEquals("value0", mruCache.get("key0"));
        assertEquals("largeValue", mruCache.get("largeKey"));
    }

    @Test
    public void testEmptyCacheBehavior() {
        MRUCache<Integer, Integer> mruCache = new MRUCache<>(SIZE);

        // Verify that accessing any key returns null
        assertNull(mruCache.get(1));
        assertNull(mruCache.get(100));

        // Adding to cache and checking again
        mruCache.put(1, 10);
        assertEquals(10, mruCache.get(1));
    }
}
