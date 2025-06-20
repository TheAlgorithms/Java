package com.thealgorithms.datastructures.caches;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RRCacheTest {

    private RRCache<String, String> cache;
    private List<String> evictedKeys;
    private List<String> evictedValues;

    @BeforeEach
    void setUp() {
        evictedKeys = new ArrayList<>();
        evictedValues = new ArrayList<>();

        cache = new RRCache.Builder<String, String>(3)
                .defaultTTL(1000)
                .random(new Random(0))
                .evictionListener((k, v) -> {
                    evictedKeys.add(k);
                    evictedValues.add(v);
                })
                .build();
    }

    @Test
    void testPutAndGet() {
        cache.put("a", "apple");
        assertEquals("apple", cache.get("a"));
    }

    @Test
    void testOverwriteValue() {
        cache.put("a", "apple");
        cache.put("a", "avocado");
        assertEquals("avocado", cache.get("a"));
    }

    @Test
    void testExpiration() throws InterruptedException {
        cache.put("temp", "value", 100); // short TTL
        Thread.sleep(200);
        assertNull(cache.get("temp"));
        assertTrue(evictedKeys.contains("temp"));
    }

    @Test
    void testEvictionOnCapacity() {
        cache.put("a", "alpha");
        cache.put("b", "bravo");
        cache.put("c", "charlie");
        cache.put("d", "delta"); // triggers eviction

        int size = cache.size();
        assertEquals(3, size);
        assertEquals(1, evictedKeys.size());
        assertEquals(1, evictedValues.size());
    }

    @Test
    void testEvictionListener() {
        cache.put("x", "one");
        cache.put("y", "two");
        cache.put("z", "three");
        cache.put("w", "four"); // one of x, y, z will be evicted

        assertFalse(evictedKeys.isEmpty());
        assertFalse(evictedValues.isEmpty());
    }

    @Test
    void testHitsAndMisses() {
        cache.put("a", "apple");
        assertEquals("apple", cache.get("a"));
        assertNull(cache.get("b"));
        assertEquals(1, cache.getHits());
        assertEquals(1, cache.getMisses());
    }

    @Test
    void testSizeExcludesExpired() throws InterruptedException {
        cache.put("a", "a", 100);
        cache.put("b", "b", 100);
        cache.put("c", "c", 100);
        Thread.sleep(150);
        assertEquals(0, cache.size());
    }

    @Test
    void testToStringDoesNotExposeExpired() throws InterruptedException {
        cache.put("live", "alive");
        cache.put("dead", "gone", 100);
        Thread.sleep(150);
        String result = cache.toString();
        assertTrue(result.contains("live"));
        assertFalse(result.contains("dead"));
    }

    @Test
    void testNullKeyGetThrows() {
        assertThrows(IllegalArgumentException.class, () -> cache.get(null));
    }

    @Test
    void testPutNullKeyThrows() {
        assertThrows(IllegalArgumentException.class, () -> cache.put(null, "v"));
    }

    @Test
    void testPutNullValueThrows() {
        assertThrows(IllegalArgumentException.class, () -> cache.put("k", null));
    }

    @Test
    void testPutNegativeTTLThrows() {
        assertThrows(IllegalArgumentException.class, () -> cache.put("k", "v", -1));
    }

    @Test
    void testBuilderNegativeCapacityThrows() {
        assertThrows(IllegalArgumentException.class, () -> new RRCache.Builder<>(0));
    }

    @Test
    void testBuilderNullRandomThrows() {
        RRCache.Builder<String, String> builder = new RRCache.Builder<>(1);
        assertThrows(IllegalArgumentException.class, () -> builder.random(null));
    }

    @Test
    void testBuilderNullEvictionListenerThrows() {
        RRCache.Builder<String, String> builder = new RRCache.Builder<>(1);
        assertThrows(IllegalArgumentException.class, () -> builder.evictionListener(null));
    }

    @Test
    void testEvictionListenerExceptionDoesNotCrash() {
        RRCache<String, String> listenerCache = new RRCache.Builder<String, String>(1)
                .evictionListener((k, v) -> {
                    throw new RuntimeException("Exception");
                })
                .build();

        listenerCache.put("a", "a");
        listenerCache.put("b", "b"); // causes eviction but should not crash
        assertDoesNotThrow(() -> listenerCache.get("a"));
    }

    @Test
    void testTtlZeroThrowsIllegalArgumentException() {
        Executable exec = () -> new RRCache.Builder<String, String>(3)
                .defaultTTL(-1)
                .build();
        assertThrows(IllegalArgumentException.class, exec);
    }
}
