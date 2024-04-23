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

    public ARCCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>();
        this.usageCounts = new LinkedHashMap<>();
        this.p = capacity / 2;
        this.totalCount = 0;
    }

    // Function to get data from cache corresponding to the key passed as parameter
    public V get(K key) {
        if (cache.containsKey(key)) {
            usageCounts.put(key, usageCounts.getOrDefault(key, 0) + 1);
            return cache.get(key);
        }
        return null;
    }

    // Function to put the data in the cache corresponding to the key passed as parameter
    public void put(K key, V value) {
        if (cache.size() >= capacity) {
            evict();
        }
        cache.put(key, value);
        usageCounts.put(key, 1);
        totalCount++;
    }

    // Function to implement the logic of ARCCache
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
