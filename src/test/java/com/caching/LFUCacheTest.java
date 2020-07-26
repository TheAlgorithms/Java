package com.caching;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LFUCacheTest {

    @Test
    public void testLFUCache() {
        LFUCache<Integer> cache = new LFUCache<Integer>(2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        Assertions.assertEquals(1, cache.get(1)); // returns 1
        cache.put(3, 3); // evicts key 2
        Assertions.assertThrows(NoSuchElementException.class, () -> cache.get(2));// throws exception
        Assertions.assertEquals(3, cache.get(3)); // returns 3.
        cache.put(4, 4); // evicts key 1.
        Assertions.assertThrows(NoSuchElementException.class, () -> cache.get(1));// throws exception
        Assertions.assertEquals(3, cache.get(3)); // returns 3
        Assertions.assertEquals(4, cache.get(4)); // returns 4
    }
}