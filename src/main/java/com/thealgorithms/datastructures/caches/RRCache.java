package com.thealgorithms.datastructures.caches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;

/**
 * A thread-safe generic cache implementation using the Random Replacement (RR) eviction policy.
 * <p>
 * The cache holds a fixed number of entries, defined by its capacity. When the cache is full and a
 * new entry is added, one of the existing entries is selected at random and evicted to make space.
 * <p>
 * Optionally, entries can have a time-to-live (TTL) in milliseconds. If a TTL is set, entries will
 * automatically expire and be removed upon access or insertion attempts.
 * <p>
 * Features:
 * <ul>
 *     <li>Random eviction when capacity is exceeded</li>
 *     <li>Optional TTL (time-to-live in milliseconds) per entry or default TTL for all entries</li>
 *     <li>Thread-safe access using locking</li>
 *     <li>Hit and miss counters for cache statistics</li>
 *     <li>Eviction listener callback support</li>
 * </ul>
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 * See <a href="https://en.wikipedia.org/wiki/Cache_replacement_policies#Random_replacement_(RR)">Random Replacement</a>
 * @author Kevin Babu (<a href="https://www.github.com/KevinMwita7">GitHub</a>)
 */
public final class RRCache<K, V> {

    private final int capacity;
    private final long defaultTTL;
    private final Map<K, CacheEntry<V>> cache;
    private final List<K> keys;
    private final Random random;
    private final Lock lock;

    private long hits = 0;
    private long misses = 0;
    private final BiConsumer<K, V> evictionListener;
    private final EvictionStrategy<K, V> evictionStrategy;

    /**
     * Internal structure to store value + expiry timestamp.
     *
     * @param <V> the type of the value being cached
     */
    private static class CacheEntry<V> {
        V value;
        long expiryTime;

        /**
         * Constructs a new {@code CacheEntry} with the specified value and time-to-live (TTL).
         *
         * @param value     the value to cache
         * @param ttlMillis the time-to-live in milliseconds
         */
        CacheEntry(V value, long ttlMillis) {
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + ttlMillis;
        }

        /**
         * Checks if the cache entry has expired.
         *
         * @return {@code true} if the current time is past the expiration time; {@code false} otherwise
         */
        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    /**
     * Constructs a new {@code RRCache} instance using the provided {@link Builder}.
     *
     * <p>This constructor initializes the cache with the specified capacity and default TTL,
     * sets up internal data structures (a {@code HashMap} for cache entries and an {@code ArrayList}
     * for key tracking), and configures eviction and randomization behavior.
     *
     * @param builder the {@code Builder} object containing configuration parameters
     */
    private RRCache(Builder<K, V> builder) {
        this.capacity = builder.capacity;
        this.defaultTTL = builder.defaultTTL;
        this.cache = new HashMap<>(builder.capacity);
        this.keys = new ArrayList<>(builder.capacity);
        this.random = builder.random != null ? builder.random : new Random();
        this.lock = new ReentrantLock();
        this.evictionListener = builder.evictionListener;
        this.evictionStrategy = builder.evictionStrategy;
    }

    /**
     * Retrieves the value associated with the specified key from the cache.
     *
     * <p>If the key is not present or the corresponding entry has expired, this method
     * returns {@code null}. If an expired entry is found, it will be removed and the
     * eviction listener (if any) will be notified. Cache hit-and-miss statistics are
     * also updated accordingly.
     *
     * @param key the key whose associated value is to be returned; must not be {@code null}
     * @return the cached value associated with the key, or {@code null} if not present or expired
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        lock.lock();
        try {
            evictionStrategy.onAccess(this);

            CacheEntry<V> entry = cache.get(key);
            if (entry == null || entry.isExpired()) {
                if (entry != null) {
                    removeKey(key);
                    notifyEviction(key, entry.value);
                }
                misses++;
                return null;
            }
            hits++;
            return entry.value;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Adds a key-value pair to the cache using the default time-to-live (TTL).
     *
     * <p>The key may overwrite an existing entry. The actual insertion is delegated
     * to the overloaded {@link #put(K, V, long)} method.
     *
     * @param key   the key to cache the value under
     * @param value the value to be cached
     */
    public void put(K key, V value) {
        put(key, value, defaultTTL);
    }

    /**
     * Adds a key-value pair to the cache with a specified time-to-live (TTL).
     *
     * <p>If the key already exists, its value is updated and its TTL is reset. If the key
     * does not exist and the cache is full, a random entry is evicted to make space.
     * Expired entries are also cleaned up prior to any eviction. The eviction listener
     * is notified when an entry gets evicted.
     *
     * @param key        the key to associate with the cached value; must not be {@code null}
     * @param value      the value to be cached; must not be {@code null}
     * @param ttlMillis  the time-to-live for this entry in milliseconds; must be >= 0
     * @throws IllegalArgumentException if {@code key} or {@code value} is {@code null}, or if {@code ttlMillis} is negative
     */
    public void put(K key, V value, long ttlMillis) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value must not be null");
        }
        if (ttlMillis < 0) {
            throw new IllegalArgumentException("TTL must be >= 0");
        }

        lock.lock();
        try {
            if (cache.containsKey(key)) {
                cache.put(key, new CacheEntry<>(value, ttlMillis));
                return;
            }

            evictExpired();

            if (cache.size() >= capacity) {
                int idx = random.nextInt(keys.size());
                K evictKey = keys.remove(idx);
                CacheEntry<V> evictVal = cache.remove(evictKey);
                notifyEviction(evictKey, evictVal.value);
            }

            cache.put(key, new CacheEntry<>(value, ttlMillis));
            keys.add(key);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Removes all expired entries from the cache.
     *
     * <p>This method iterates through the list of cached keys and checks each associated
     * entry for expiration. Expired entries are removed from both the key tracking list
     * and the cache map. For each eviction, the eviction listener is notified.
     */
    private int evictExpired() {
        Iterator<K> it = keys.iterator();
        int expiredCount = 0;

        while (it.hasNext()) {
            K k = it.next();
            CacheEntry<V> entry = cache.get(k);
            if (entry != null && entry.isExpired()) {
                it.remove();
                cache.remove(k);
                ++expiredCount;
                notifyEviction(k, entry.value);
            }
        }
        return expiredCount;
    }

    /**
     * Removes the specified key and its associated entry from the cache.
     *
     * <p>This method deletes the key from both the cache map and the key tracking list.
     *
     * @param key the key to remove from the cache
     */
    private void removeKey(K key) {
        cache.remove(key);
        keys.remove(key);
    }

    /**
     * Notifies the eviction listener, if one is registered, that a key-value pair has been evicted.
     *
     * <p>If the {@code evictionListener} is not {@code null}, it is invoked with the provided key
     * and value. Any exceptions thrown by the listener are caught and logged to standard error,
     * preventing them from disrupting cache operations.
     *
     * @param key   the key that was evicted
     * @param value the value that was associated with the evicted key
     */
    private void notifyEviction(K key, V value) {
        if (evictionListener != null) {
            try {
                evictionListener.accept(key, value);
            } catch (Exception e) {
                System.err.println("Eviction listener failed: " + e.getMessage());
            }
        }
    }

    /**
     * Returns the number of successful cache lookups (hits).
     *
     * @return the number of cache hits
     */
    public long getHits() {
        lock.lock();
        try {
            return hits;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns the number of failed cache lookups (misses), including expired entries.
     *
     * @return the number of cache misses
     */
    public long getMisses() {
        lock.lock();
        try {
            return misses;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns the current number of entries in the cache, excluding expired ones.
     *
     * @return the current cache size
     */
    public int size() {
        lock.lock();
        try {
            int cachedSize = cache.size();
            int evictedCount = evictionStrategy.onAccess(this);
            if (evictedCount > 0) {
                return cachedSize - evictedCount;
            }

            // This runs if periodic eviction does not occur
            int count = 0;
            for (Map.Entry<K, CacheEntry<V>> entry : cache.entrySet()) {
                if (!entry.getValue().isExpired()) {
                    ++count;
                }
            }
            return count;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns the current {@link EvictionStrategy} used by this cache instance.

     * @return the eviction strategy currently assigned to this cache
     */
    public EvictionStrategy<K, V> getEvictionStrategy() {
        return evictionStrategy;
    }

    /**
     * Returns a string representation of the cache, including metadata and current non-expired entries.
     *
     * <p>The returned string includes the cache's capacity, current size (excluding expired entries),
     * hit-and-miss counts, and a map of all non-expired key-value pairs. This method acquires a lock
     * to ensure thread-safe access.
     *
     * @return a string summarizing the state of the cache
     */
    @Override
    public String toString() {
        lock.lock();
        try {
            Map<K, V> visible = new HashMap<>();
            for (Map.Entry<K, CacheEntry<V>> entry : cache.entrySet()) {
                if (!entry.getValue().isExpired()) {
                    visible.put(entry.getKey(), entry.getValue().value);
                }
            }
            return String.format("Cache(capacity=%d, size=%d, hits=%d, misses=%d, entries=%s)", capacity, visible.size(), hits, misses, visible);
        } finally {
            lock.unlock();
        }
    }

    /**
     * A strategy interface for controlling when expired entries are evicted from the cache.
     *
     * <p>Implementations decide whether and when to trigger {@link RRCache#evictExpired()} based
     * on cache usage patterns. This allows for flexible eviction behaviour such as periodic cleanup,
     * or no automatic cleanup.
     *
     * @param <K> the type of keys maintained by the cache
     * @param <V> the type of cached values
     */
    public interface EvictionStrategy<K, V> {
        /**
         * Called on each cache access (e.g., {@link RRCache#get(Object)}) to optionally trigger eviction.
         *
         * @param cache the cache instance on which this strategy is applied
         * @return the number of expired entries evicted during this access
         */
        int onAccess(RRCache<K, V> cache);
    }

    /**
     * An eviction strategy that performs eviction of expired entries on each call.
     *
     * @param <K> the type of keys
     * @param <V> the type of values
     */
    public static class NoEvictionStrategy<K, V> implements EvictionStrategy<K, V> {
        @Override
        public int onAccess(RRCache<K, V> cache) {
            return cache.evictExpired();
        }
    }

    /**
     * An eviction strategy that triggers eviction every fixed number of accesses.
     *
     * <p>This deterministic strategy ensures cleanup occurs at predictable intervals,
     * ideal for moderately active caches where memory usage is a concern.
     *
     * @param <K> the type of keys
     * @param <V> the type of values
     */
    public static class PeriodicEvictionStrategy<K, V> implements EvictionStrategy<K, V> {
        private final int interval;
        private int counter = 0;

        /**
         * Constructs a periodic eviction strategy.
         *
         * @param interval the number of accesses between evictions; must be > 0
         * @throws IllegalArgumentException if {@code interval} is less than or equal to 0
         */
        public PeriodicEvictionStrategy(int interval) {
            if (interval <= 0) {
                throw new IllegalArgumentException("Interval must be > 0");
            }
            this.interval = interval;
        }

        @Override
        public int onAccess(RRCache<K, V> cache) {
            if (++counter % interval == 0) {
                return cache.evictExpired();
            }

            return 0;
        }
    }

    /**
     * A builder for constructing an {@link RRCache} instance with customizable settings.
     *
     * <p>Allows configuring capacity, default TTL, random eviction behavior, eviction listener,
     * and a pluggable eviction strategy. Call {@link #build()} to create the configured cache instance.
     *
     * @param <K> the type of keys maintained by the cache
     * @param <V> the type of values stored in the cache
     */
    public static class Builder<K, V> {
        private final int capacity;
        private long defaultTTL = 0;
        private Random random;
        private BiConsumer<K, V> evictionListener;
        private EvictionStrategy<K, V> evictionStrategy = new RRCache.PeriodicEvictionStrategy<>(100);
        /**
         * Creates a new {@code Builder} with the specified cache capacity.
         *
         * @param capacity the maximum number of entries the cache can hold; must be > 0
         * @throws IllegalArgumentException if {@code capacity} is less than or equal to 0
         */
        public Builder(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Capacity must be > 0");
            }
            this.capacity = capacity;
        }

        /**
         * Sets the default time-to-live (TTL) in milliseconds for cache entries.
         *
         * @param ttlMillis the TTL duration in milliseconds; must be >= 0
         * @return this builder instance for chaining
         * @throws IllegalArgumentException if {@code ttlMillis} is negative
         */
        public Builder<K, V> defaultTTL(long ttlMillis) {
            if (ttlMillis < 0) {
                throw new IllegalArgumentException("Default TTL must be >= 0");
            }
            this.defaultTTL = ttlMillis;
            return this;
        }

        /**
         * Sets the {@link Random} instance to be used for random eviction selection.
         *
         * @param r a non-null {@code Random} instance
         * @return this builder instance for chaining
         * @throws IllegalArgumentException if {@code r} is {@code null}
         */
        public Builder<K, V> random(Random r) {
            if (r == null) {
                throw new IllegalArgumentException("Random must not be null");
            }
            this.random = r;
            return this;
        }

        /**
         * Sets an eviction listener to be notified when entries are evicted from the cache.
         *
         * @param listener a {@link BiConsumer} that accepts evicted keys and values; must not be {@code null}
         * @return this builder instance for chaining
         * @throws IllegalArgumentException if {@code listener} is {@code null}
         */
        public Builder<K, V> evictionListener(BiConsumer<K, V> listener) {
            if (listener == null) {
                throw new IllegalArgumentException("Listener must not be null");
            }
            this.evictionListener = listener;
            return this;
        }

        /**
         * Builds and returns a new {@link RRCache} instance with the configured parameters.
         *
         * @return a fully configured {@code RRCache} instance
         */
        public RRCache<K, V> build() {
            return new RRCache<>(this);
        }

        /**
         * Sets the eviction strategy used to determine when to clean up expired entries.
         *
         * @param strategy an {@link EvictionStrategy} implementation; must not be {@code null}
         * @return this builder instance
         * @throws IllegalArgumentException if {@code strategy} is {@code null}
         */
        public Builder<K, V> evictionStrategy(EvictionStrategy<K, V> strategy) {
            if (strategy == null) {
                throw new IllegalArgumentException("Eviction strategy must not be null");
            }
            this.evictionStrategy = strategy;
            return this;
        }
    }
}
