package com.thealgorithms.datastructures.caches;

import java.util.HashMap;
import java.util.Map;

/**
 * A Least Recently Used (LRU) Cache implementation.
 *
 * <p>An LRU cache is a fixed-size cache that maintains items in order of use. When the cache reaches
 * its capacity and a new item needs to be added, it removes the least recently used item first.
 * This implementation provides O(1) time complexity for both get and put operations.</p>
 *
 * <p>Features:</p>
 * <ul>
 *   <li>Fixed-size cache with configurable capacity</li>
 *   <li>Constant time O(1) operations for get and put</li>
 *   <li>Thread-unsafe - should be externally synchronized if used in concurrent environments</li>
 *   <li>Supports null values but not null keys</li>
 * </ul>
 *
 * <p>Implementation Details:</p>
 * <ul>
 *   <li>Uses a HashMap for O(1) key-value lookups</li>
 *   <li>Maintains a doubly-linked list for tracking access order</li>
 *   <li>The head of the list contains the least recently used item</li>
 *   <li>The tail of the list contains the most recently used item</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 * LRUCache<String, Integer> cache = new LRUCache<>(3); // Create cache with capacity 3
 * cache.put("A", 1); // Cache: A=1
 * cache.put("B", 2); // Cache: A=1, B=2
 * cache.put("C", 3); // Cache: A=1, B=2, C=3
 * cache.get("A");    // Cache: B=2, C=3, A=1 (A moved to end)
 * cache.put("D", 4); // Cache: C=3, A=1, D=4 (B evicted)
 * </pre>
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 */
public class LRUCache<K, V> {

    private final Map<K, Entry<K, V>> data = new HashMap<>();
    private Entry<K, V> head;
    private Entry<K, V> tail;
    private int cap;
    private static final int DEFAULT_CAP = 100;

    public LRUCache() {
        setCapacity(DEFAULT_CAP);
    }

    public LRUCache(int cap) {
        setCapacity(cap);
    }

    /**
     * Returns the current capacity of the cache.
     *
     * @param newCapacity the new capacity of the cache
     */
    private void setCapacity(int newCapacity) {
        checkCapacity(newCapacity);
        for (int i = data.size(); i > newCapacity; i--) {
            Entry<K, V> evicted = evict();
            data.remove(evicted.getKey());
        }
        this.cap = newCapacity;
    }

    /**
     * Evicts the least recently used item from the cache.
     *
     * @return the evicted entry
     */
    private Entry<K, V> evict() {
        if (head == null) {
            throw new RuntimeException("cache cannot be empty!");
        }
        Entry<K, V> evicted = head;
        head = evicted.getNextEntry();
        head.setPreEntry(null);
        evicted.setNextEntry(null);
        return evicted;
    }

    /**
     * Checks if the capacity is valid.
     *
     * @param capacity the capacity to check
     */
    private void checkCapacity(int capacity) {
        if (capacity <= 0) {
            throw new RuntimeException("capacity must greater than 0!");
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this cache contains no
     * mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this cache contains no
     * mapping for the key
     */
    public V get(K key) {
        if (!data.containsKey(key)) {
            return null;
        }
        final Entry<K, V> entry = data.get(key);
        moveNodeToLast(entry);
        return entry.getValue();
    }

    /**
     * Moves the specified entry to the end of the list.
     *
     * @param entry the entry to move
     */
    private void moveNodeToLast(Entry<K, V> entry) {
        if (tail == entry) {
            return;
        }
        final Entry<K, V> preEntry = entry.getPreEntry();
        final Entry<K, V> nextEntry = entry.getNextEntry();
        if (preEntry != null) {
            preEntry.setNextEntry(nextEntry);
        }
        if (nextEntry != null) {
            nextEntry.setPreEntry(preEntry);
        }
        if (head == entry) {
            head = nextEntry;
        }
        tail.setNextEntry(entry);
        entry.setPreEntry(tail);
        entry.setNextEntry(null);
        tail = entry;
    }

    /**
     * Associates the specified value with the specified key in this cache.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    public void put(K key, V value) {
        if (data.containsKey(key)) {
            final Entry<K, V> existingEntry = data.get(key);
            existingEntry.setValue(value);
            moveNodeToLast(existingEntry);
            return;
        }
        Entry<K, V> newEntry;
        if (data.size() == cap) {
            newEntry = evict();
            data.remove(newEntry.getKey());
        } else {
            newEntry = new Entry<>();
        }

        newEntry.setKey(key);
        newEntry.setValue(value);
        addNewEntry(newEntry);
        data.put(key, newEntry);
    }

    /**
     * Adds a new entry to the end of the list.
     *
     * @param newEntry the entry to add
     */
    private void addNewEntry(Entry<K, V> newEntry) {
        if (data.isEmpty()) {
            head = newEntry;
            tail = newEntry;
            return;
        }
        tail.setNextEntry(newEntry);
        newEntry.setPreEntry(tail);
        newEntry.setNextEntry(null);
        tail = newEntry;
    }

    static final class Entry<I, J> {

        private Entry<I, J> preEntry;
        private Entry<I, J> nextEntry;
        private I key;
        private J value;

        Entry() {
        }

        Entry(Entry<I, J> preEntry, Entry<I, J> nextEntry, I key, J value) {
            this.preEntry = preEntry;
            this.nextEntry = nextEntry;
            this.key = key;
            this.value = value;
        }

        public Entry<I, J> getPreEntry() {
            return preEntry;
        }

        public void setPreEntry(Entry<I, J> preEntry) {
            this.preEntry = preEntry;
        }

        public Entry<I, J> getNextEntry() {
            return nextEntry;
        }

        public void setNextEntry(Entry<I, J> nextEntry) {
            this.nextEntry = nextEntry;
        }

        public I getKey() {
            return key;
        }

        public void setKey(I key) {
            this.key = key;
        }

        public J getValue() {
            return value;
        }

        public void setValue(J value) {
            this.value = value;
        }
    }
}
