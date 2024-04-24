package com.thealgorithms.datastructures.caches;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * Adaptive Replacement Cache (ARC)
 * <p>
 *     dynamically adjusts cache size based on recent access patterns.
 *     It aims to provide better performance compared to traditional caching algorithms
 *     like LRU (Least Recently Used) and LFU (Least Frequently Used).
 *     It combines elements of LRU (Least Recently Used) and LFU (Least Frequently Used) algorithms
 *     to efficiently manage frequently accessed and recently used items,
 *     optimizing cache performance in changing workload scenarios.
 * <a href="https://en.wikipedia.org/wiki/Adaptive_replacement_cache">...</a>
 * @author Adarsh Pandey (<a href="https://github.com/devxadarsh">...</a>)
 *
 * @param <K> key type
 * @param <V> value type
 */

public class ARCCache<K, V> {
    private final int capacity;
    private final Map<K, V> cache;
    private final LinkedHashMap<K, Integer> usageCounts;
    private final int p;
    private int totalCount;
/**
 * Retrieves the value associated with the given key from the cache.
 * If the key is present in the cache, its usage count is incremented.
 *
 * @param key the key whose associated value is to be retrieved
 * @return the value associated with the key, or null if the key is not present in the cache
 */
    public ARCCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>();
        this.usageCounts = new LinkedHashMap<>();
        this.p = capacity / 2;
        this.totalCount = 0;
    }

    /**
     * Retrieves the value associated with the given key from the cache.
     * If the key is present in the cache, its usage count is incremented.
     *
     * @param key the key whose associated value is to be retrieved
     * @return the value associated with the key, or null if the key is not present in the cache
     */
    public V get(K key) {
        if (cache.containsKey(key)) {
            usageCounts.put(key, usageCounts.getOrDefault(key, 0) + 1);
            return cache.get(key);
        }
        return null;
    }

    /**
     * Adds the specified key-value pair to the cache.
     * If the cache exceeds its capacity after adding the new entry, eviction is performed.
     * Updates the usage count for the added key.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    public void put(K key, V value) {
        if (cache.size() >= capacity) {
            evict();
        }
        cache.put(key, value);
        usageCounts.put(key, 1);
        totalCount++;
    }

    /**
     * Evicts an item from the cache when it exceeds its capacity.
     * Implements the Adaptive Replacement Cache (ARC) algorithm logic for eviction.
     * Removes the least recently used item based on its usage count.
     */
    private void evict() {
        if (!cache.isEmpty()) {
            K keyToRemove = null;
            int minUsageCount = Integer.MAX_VALUE;
            for (Map.Entry<K, Integer> entry : usageCounts.entrySet()) {
                if (entry.getValue() < minUsageCount) {
                    keyToRemove = entry.getKey();
                    minUsageCount = entry.getValue();
                }
            }
            cache.remove(keyToRemove);
            usageCounts.remove(keyToRemove);
            totalCount--;
        }
    }
}
