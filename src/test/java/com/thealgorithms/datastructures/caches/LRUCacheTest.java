package com.thealgorithms.datastructures.caches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LRUCacheTest {
    private static final int SIZE = 5;
    private LRUCache<Integer, Integer> cache;

    @BeforeEach
    void setUp() {
        cache = new LRUCache<>(SIZE);
    }

    @Test
    public void testBasicOperations() {
        cache.put(1, 100);
        assertEquals(100, cache.get(1));
        assertNull(cache.get(2));
    }

    @Test
    public void testEvictionPolicy() {
        // Fill cache to capacity
        for (int i = 0; i < SIZE; i++) {
            cache.put(i, i * 100);
        }

        // Verify all elements are present
        for (int i = 0; i < SIZE; i++) {
            assertEquals(i * 100, cache.get(i));
        }

        // Add one more element, causing eviction of least recently used
        cache.put(SIZE, SIZE * 100);

        // First element should be evicted
        assertNull(cache.get(0));
        assertEquals(SIZE * 100, cache.get(SIZE));
    }

    @Test
    public void testAccessOrder() {
        // Fill cache
        for (int i = 0; i < SIZE; i++) {
            cache.put(i, i);
        }

        // Access first element, making it most recently used
        cache.get(0);

        // Add new element, should evict second element (1)
        cache.put(SIZE, SIZE);

        assertEquals(0, cache.get(0)); // Should still exist
        assertNull(cache.get(1)); // Should be evicted
        assertEquals(SIZE, cache.get(SIZE)); // Should exist
    }

    @Test
    public void testUpdateExistingKey() {
        cache.put(1, 100);
        assertEquals(100, cache.get(1));

        // Update existing key
        cache.put(1, 200);
        assertEquals(200, cache.get(1));
    }

    @Test
    public void testNullValues() {
        cache.put(1, null);
        assertNull(cache.get(1));

        // Update null to non-null
        cache.put(1, 100);
        assertEquals(100, cache.get(1));

        // Update non-null to null
        cache.put(1, null);
        assertNull(cache.get(1));
    }

    @Test
    public void testStringKeysAndValues() {
        LRUCache<String, String> stringCache = new LRUCache<>(SIZE);

        stringCache.put("key1", "value1");
        stringCache.put("key2", "value2");

        assertEquals("value1", stringCache.get("key1"));
        assertEquals("value2", stringCache.get("key2"));
    }

    @Test
    public void testLongSequenceOfOperations() {
        // Add elements beyond capacity multiple times
        for (int i = 0; i < SIZE * 3; i++) {
            cache.put(i, i * 100);

            // Verify only the last SIZE elements are present
            for (int j = Math.max(0, i - SIZE + 1); j <= i; j++) {
                assertEquals(j * 100, cache.get(j));
            }

            // Verify elements before the window are evicted
            if (i >= SIZE) {
                assertNull(cache.get(i - SIZE));
            }
        }
    }

    @Test
    void testCustomObjects() {
        class TestObject {
            private final String value;

            TestObject(String value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj instanceof TestObject) {
                    return value.equals(((TestObject) obj).value);
                }
                return false;
            }

            @Override
            public int hashCode() {
                return value == null ? 0 : value.hashCode();
            }
        }

        LRUCache<Integer, TestObject> objectCache = new LRUCache<>(SIZE);
        TestObject obj1 = new TestObject("test1");
        TestObject obj2 = new TestObject("test2");

        objectCache.put(1, obj1);
        objectCache.put(2, obj2);

        assertEquals(obj1, objectCache.get(1));
        assertEquals(obj2, objectCache.get(2));
    }
}
