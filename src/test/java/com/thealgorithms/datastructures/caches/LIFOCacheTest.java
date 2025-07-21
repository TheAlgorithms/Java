package com.thealgorithms.datastructures.caches;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class LIFOCacheTest {
    private LIFOCache<String, String> cache;
    private Set<String> evictedKeys;
    private List<String> evictedValues;

    @BeforeEach
    void setUp() {
        evictedKeys = new HashSet<>();
        evictedValues = new ArrayList<>();

        cache = new LIFOCache.Builder<String, String>(3)
                    .defaultTTL(1000)
                    .evictionListener((k, v) -> {
                        evictedKeys.add(k);
                        evictedValues.add(v);
                    })
                    .build();
    }

    @Test
    void testPutAndGet() {
        cache.put("a", "apple");
        Assertions.assertEquals("apple", cache.get("a"));
    }

    @Test
    void testOverwriteValue() {
        cache.put("a", "apple");
        cache.put("a", "avocado");
        Assertions.assertEquals("avocado", cache.get("a"));
    }

    @Test
    void testExpiration() throws InterruptedException {
        cache.put("temp", "value", 100);
        Thread.sleep(200);
        Assertions.assertNull(cache.get("temp"));
        Assertions.assertTrue(evictedKeys.contains("temp"));
    }

    @Test
    void testEvictionOnCapacity() {
        cache.put("a", "alpha");
        cache.put("b", "bravo");
        cache.put("c", "charlie");
        cache.put("d", "delta");

        int size = cache.size();
        Assertions.assertEquals(3, size);
        Assertions.assertEquals(1, evictedKeys.size());
        Assertions.assertEquals(1, evictedValues.size());
        Assertions.assertEquals("charlie", evictedValues.getFirst());
    }

    @Test
    void testEvictionListener() {
        cache.put("x", "one");
        cache.put("y", "two");
        cache.put("z", "three");
        cache.put("w", "four");

        Assertions.assertFalse(evictedKeys.isEmpty());
        Assertions.assertFalse(evictedValues.isEmpty());
    }

    @Test
    void testHitsAndMisses() {
        cache.put("a", "apple");
        Assertions.assertEquals("apple", cache.get("a"));
        Assertions.assertNull(cache.get("b"));
        Assertions.assertEquals(1, cache.getHits());
        Assertions.assertEquals(1, cache.getMisses());
    }

    @Test
    void testSizeExcludesExpired() throws InterruptedException {
        cache.put("a", "a", 100);
        cache.put("b", "b", 100);
        cache.put("c", "c", 100);
        Thread.sleep(150);
        Assertions.assertEquals(0, cache.size());
    }

    @Test
    void testSizeIncludesFresh() {
        cache.put("a", "a", 1000);
        cache.put("b", "b", 1000);
        cache.put("c", "c", 1000);
        Assertions.assertEquals(3, cache.size());
    }

    @Test
    void testToStringDoesNotExposeExpired() throws InterruptedException {
        cache.put("live", "alive");
        cache.put("dead", "gone", 100);
        Thread.sleep(150);
        String result = cache.toString();
        Assertions.assertTrue(result.contains("live"));
        Assertions.assertFalse(result.contains("dead"));
    }

    @Test
    void testNullKeyGetThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cache.get(null));
    }

    @Test
    void testPutNullKeyThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cache.put(null, "v"));
    }

    @Test
    void testPutNullValueThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cache.put("k", null));
    }

    @Test
    void testPutNegativeTTLThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cache.put("k", "v", -1));
    }

    @Test
    void testBuilderNegativeCapacityThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new LIFOCache.Builder<>(0));
    }

    @Test
    void testBuilderNullEvictionListenerThrows() {
        LIFOCache.Builder<String, String> builder = new LIFOCache.Builder<>(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> builder.evictionListener(null));
    }

    @Test
    void testEvictionListenerExceptionDoesNotCrash() {
        LIFOCache<String, String> listenerCache = new LIFOCache.Builder<String, String>(1).evictionListener((k, v) -> { throw new RuntimeException("Exception"); }).build();

        listenerCache.put("a", "a");
        listenerCache.put("b", "b");
        Assertions.assertDoesNotThrow(() -> listenerCache.get("a"));
    }

    @Test
    void testTtlZeroThrowsIllegalArgumentException() {
        Executable exec = () -> new LIFOCache.Builder<String, String>(3).defaultTTL(-1).build();
        Assertions.assertThrows(IllegalArgumentException.class, exec);
    }

    @Test
    void testPeriodicEvictionStrategyEvictsAtInterval() throws InterruptedException {
        LIFOCache<String, String> periodicCache = new LIFOCache.Builder<String, String>(10).defaultTTL(50).evictionStrategy(new LIFOCache.PeriodicEvictionStrategy<>(3)).build();

        periodicCache.put("x", "1");
        Thread.sleep(100);
        int ev1 = periodicCache.getEvictionStrategy().onAccess(periodicCache);
        int ev2 = periodicCache.getEvictionStrategy().onAccess(periodicCache);
        int ev3 = periodicCache.getEvictionStrategy().onAccess(periodicCache);

        Assertions.assertEquals(0, ev1);
        Assertions.assertEquals(0, ev2);
        Assertions.assertEquals(1, ev3, "Eviction should happen on the 3rd access");
        Assertions.assertEquals(0, periodicCache.size());
    }

    @Test
    void testPeriodicEvictionStrategyThrowsExceptionIfIntervalLessThanOrEqual0() {
        Executable executable = () -> new LIFOCache.Builder<String, String>(10).defaultTTL(50).evictionStrategy(new LIFOCache.PeriodicEvictionStrategy<>(0)).build();

        Assertions.assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    void testImmediateEvictionStrategyStrategyEvictsOnEachCall() throws InterruptedException {
        LIFOCache<String, String> immediateEvictionStrategy = new LIFOCache.Builder<String, String>(10).defaultTTL(50).evictionStrategy(new LIFOCache.ImmediateEvictionStrategy<>()).build();

        immediateEvictionStrategy.put("x", "1");
        Thread.sleep(100);
        int evicted = immediateEvictionStrategy.getEvictionStrategy().onAccess(immediateEvictionStrategy);

        Assertions.assertEquals(1, evicted);
    }

    @Test
    void testBuilderThrowsExceptionIfEvictionStrategyNull() {
        Executable executable = () -> new LIFOCache.Builder<String, String>(10).defaultTTL(50).evictionStrategy(null).build();

        Assertions.assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    void testReturnsCorrectStrategyInstance() {
        LIFOCache.EvictionStrategy<String, String> strategy = new LIFOCache.ImmediateEvictionStrategy<>();

        LIFOCache<String, String> newCache = new LIFOCache.Builder<String, String>(10).defaultTTL(1000).evictionStrategy(strategy).build();

        Assertions.assertSame(strategy, newCache.getEvictionStrategy(), "Returned strategy should be the same instance");
    }

    @Test
    void testDefaultStrategyIsImmediateEvictionStrategy() {
        LIFOCache<String, String> newCache = new LIFOCache.Builder<String, String>(5).defaultTTL(1000).build();

        Assertions.assertInstanceOf(LIFOCache.ImmediateEvictionStrategy.class, newCache.getEvictionStrategy(), "Default strategy should be ImmediateEvictionStrategyStrategy");
    }

    @Test
    void testGetEvictionStrategyIsNotNull() {
        LIFOCache<String, String> newCache = new LIFOCache.Builder<String, String>(5).build();

        Assertions.assertNotNull(newCache.getEvictionStrategy(), "Eviction strategy should never be null");
    }

    @Test
    void testRemoveKeyRemovesExistingKey() {
        cache.put("A", "Alpha");
        cache.put("B", "Beta");

        Assertions.assertEquals("Alpha", cache.get("A"));
        Assertions.assertEquals("Beta", cache.get("B"));

        String removed = cache.removeKey("A");
        Assertions.assertEquals("Alpha", removed);

        Assertions.assertNull(cache.get("A"));
        Assertions.assertEquals(1, cache.size());
    }

    @Test
    void testRemoveKeyReturnsNullIfKeyNotPresent() {
        cache.put("X", "X-ray");

        Assertions.assertNull(cache.removeKey("NonExistent"));
        Assertions.assertEquals(1, cache.size());
    }

    @Test
    void testRemoveKeyHandlesExpiredEntry() throws InterruptedException {
        LIFOCache<String, String> expiringCache = new LIFOCache.Builder<String, String>(2).defaultTTL(100).evictionStrategy(new LIFOCache.ImmediateEvictionStrategy<>()).build();

        expiringCache.put("T", "Temporary");

        Thread.sleep(200);

        String removed = expiringCache.removeKey("T");
        Assertions.assertEquals("Temporary", removed);
        Assertions.assertNull(expiringCache.get("T"));
    }

    @Test
    void testRemoveKeyThrowsIfKeyIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> cache.removeKey(null));
    }

    @Test
    void testRemoveKeyTriggersEvictionListener() {
        AtomicInteger evictedCount = new AtomicInteger();

        LIFOCache<String, String> localCache = new LIFOCache.Builder<String, String>(2).evictionListener((key, value) -> evictedCount.incrementAndGet()).build();

        localCache.put("A", "Apple");
        localCache.put("B", "Banana");

        localCache.removeKey("A");

        Assertions.assertEquals(1, evictedCount.get(), "Eviction listener should have been called once");
    }

    @Test
    void testRemoveKeyDoestNotAffectOtherKeys() {
        cache.put("A", "Alpha");
        cache.put("B", "Beta");
        cache.put("C", "Gamma");

        cache.removeKey("B");

        Assertions.assertEquals("Alpha", cache.get("A"));
        Assertions.assertNull(cache.get("B"));
        Assertions.assertEquals("Gamma", cache.get("C"));
    }

    @Test
    void testEvictionListenerExceptionDoesNotPropagate() {
        LIFOCache<String, String> localCache = new LIFOCache.Builder<String, String>(1).evictionListener((key, value) -> { throw new RuntimeException(); }).build();

        localCache.put("A", "Apple");

        Assertions.assertDoesNotThrow(() -> localCache.put("B", "Beta"));
    }

    @Test
    void testGetKeysReturnsAllFreshKeys() {
        cache.put("A", "Alpha");
        cache.put("B", "Beta");
        cache.put("G", "Gamma");

        Set<String> expectedKeys = Set.of("A", "B", "G");
        Assertions.assertEquals(expectedKeys, cache.getAllKeys());
    }

    @Test
    void testGetKeysIgnoresExpiredKeys() throws InterruptedException {
        cache.put("A", "Alpha");
        cache.put("B", "Beta");
        cache.put("G", "Gamma", 100);

        Set<String> expectedKeys = Set.of("A", "B");
        Thread.sleep(200);
        Assertions.assertEquals(expectedKeys, cache.getAllKeys());
    }

    @Test
    void testClearRemovesAllEntries() {
        cache.put("A", "Alpha");
        cache.put("B", "Beta");
        cache.put("G", "Gamma");

        cache.clear();
        Assertions.assertEquals(0, cache.size());
    }

    @Test
    void testGetExpiredKeyIncrementsMissesCount() throws InterruptedException {
        LIFOCache<String, String> localCache = new LIFOCache.Builder<String, String>(3).evictionStrategy(cache -> 0).defaultTTL(10).build();
        localCache.put("A", "Alpha");
        Thread.sleep(100);
        String value = localCache.get("A");
        Assertions.assertEquals(1, localCache.getMisses());
        Assertions.assertNull(value);
    }
}
