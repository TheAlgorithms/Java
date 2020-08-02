package com.caching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class LRUCacheTest {

    @Test
    public void testLFUCache() {
        LRUCache<Integer> cache = new LRUCache<Integer>(2);

        cache.put(1, 5);
        cache.put(2, 4);
        Assertions.assertEquals(5, cache.get(1)); // returns 5
        cache.put(3, 6); // evicts key 2
        Assertions.assertThrows(NoSuchElementException.class, () -> cache.get(7));// throws exception
        Assertions.assertEquals(6, cache.get(3)); // returns 6.
        cache.put(4, 8); // evicts key 1.
        Assertions.assertThrows(NoSuchElementException.class, () -> cache.get(1));// throws exception
        Assertions.assertEquals(6, cache.get(3)); // returns 6
        Assertions.assertEquals(8, cache.get(4)); // returns 8
    }
}