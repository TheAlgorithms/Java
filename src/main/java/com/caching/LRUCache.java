package com.caching;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Your LRUCache can be instantiated and called as such:
 * LRUCache lruCache = new LRUCache(capacity);
 * lruCache.put(key,value);
 * int param_1 = lruCache.get(key);
 */

public class LRUCache<T> {
    /**
     * The class LinkedHashMap,is a subclass of HashMap that preserves the insertion order.
     * We can take advantage of this class to avoid having to implement the linked list.
     * A special constructor is provided to create a linked hash map whose order of
     * iteration is the order in which its entries were least-recently (access-order).
     */
    private final LinkedHashMap<Integer, T> cache;
    private final int capacity;

    /**
     * @param capacity - Instantiates LRUCache with the given capacity.
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;

        /*
          @param loadFactor Load Factor is a measure, which decides when exactly to resize the
         *                  HashMap. By default capacity = 16 and loadFactor = 0.75f. This means
         *                  that reisze when HashMap reaches 75% of its capacity. For we will
         *                  remove an element only if the cache reaches 100% capacity (1.0f).
         *
         * @param accessOrder - Set to true if ordering mode is specified (removeEldestEntry).
         */
        this.cache = new LinkedHashMap<Integer, T>(capacity, 1.0f, true) {
            /**
             * @param eldest - The least recently accessed entry This is the entry that will
             *               be removed if the method returns {@code true}.
             *               returns {@code false} if it should be retained.
             */
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return this.size() > capacity;
            }
        };
    }


    /**
     * To put a value in LRU cache with corresponding key
     * We add the value for key only if the key is not present.
     * We don't update existing values, only access-order is updated.
     *
     * @param key   The key (int)
     * @param value The value to be cached
     */
    public void put(int key, T value) {
        if (capacity == 0) {
            System.out.println("Cache set to 0 capacity. No elements will be cached");
        }

        if (!cache.containsKey(key)) {
            cache.put(key, value);
            System.out.println("Adding new key:" + key + " to cache");
        } else {
            System.out.println("Key:" + key + " already present in cache. Access order will be updated.");
        }
    }


    /**
     * To get the cached value for given key
     *
     * @param key The key (int) of the expected value
     * @return corresponding value for input key
     * @throws NoSuchElementException if key is absent
     */
    public T get(int key) {
        // cache hit condition
        if (cache.containsKey(key)) {
            T value = cache.get(key);
            System.out.println("Returning value from cache:" + value);
            return value;
        }
        // cache miss condition
        throw new NoSuchElementException("No element found for key:" + key);
    }
}
