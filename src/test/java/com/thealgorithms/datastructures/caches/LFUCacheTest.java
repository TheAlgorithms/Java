package com.thealgorithms.datastructures.caches;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(null, lfuCache.get(2));

        // should return 60
        assertEquals(60, lfuCache.get(6));

        // this operation will remove value with key as 3
        lfuCache.put(7, 70);

        assertEquals(null, lfuCache.get(2));
        assertEquals(70, lfuCache.get(7));
    }

    @Test
    void testLFUCacheWithStringValueShouldPass() {
        LFUCache<Integer, String> lfuCache = new LFUCache<>(5);
        lfuCache.put(1, "Alpha");
        lfuCache.put(2, "Beta");
        lfuCache.put(3, "Gamma");
        lfuCache.put(4, "Delta");
        lfuCache.put(5, "Eplison");

        // get method call will increase frequency of key 1 by 1
        assertEquals("Alpha", lfuCache.get(1));

        // this operation will remove value with key as 2
        lfuCache.put(6, "Digamma");

        // will return null as value with key 2 is now evicted
        assertEquals(null, lfuCache.get(2));

        // should return string Digamma
        assertEquals("Digamma", lfuCache.get(6));

        // this operation will remove value with key as 3
        lfuCache.put(7, "Zeta");

        assertEquals(null, lfuCache.get(2));
        assertEquals("Zeta", lfuCache.get(7));
    }

    /**
     * test addNodeWithUpdatedFrequency method
     * @author yuluo
     */
    @Test
    void testAddNodeWithUpdatedFrequency() {
        LFUCache<Integer, String> lfuCache = new LFUCache<>(3);
        lfuCache.put(1, "beijing");
        lfuCache.put(2, "shanghai");
        lfuCache.put(3, "gansu");

        assertEquals("beijing", lfuCache.get(1));

        lfuCache.put(1, "shanxi");

        assertEquals("shanxi", lfuCache.get(1));
    }
}
