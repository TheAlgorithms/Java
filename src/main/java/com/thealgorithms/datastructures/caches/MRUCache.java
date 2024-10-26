package com.thealgorithms.datastructures.caches;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Most Recently Used (MRU) Cache.
 * <p>
 * In contrast to the Least Recently Used (LRU) strategy, the MRU caching policy
 * evicts the most recently accessed items first. This class provides methods to
 * store key-value pairs and manage cache eviction based on this policy.
 *
 * For more information, refer to:
 * <a href="https://en.wikipedia.org/wiki/Cache_replacement_policies#Most_recently_used_(MRU)">MRU on Wikipedia</a>.
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of values associated with the keys
 */
public class MRUCache<K, V> {

    private final Map<K, Entry<K, V>> data = new HashMap<>();
    private Entry<K, V> head;
    private Entry<K, V> tail;
    private int cap;
    private static final int DEFAULT_CAP = 100;

    /**
     * Creates an MRUCache with the default capacity.
     */
    public MRUCache() {
        setCapacity(DEFAULT_CAP);
    }

    /**
     * Creates an MRUCache with a specified capacity.
     *
     * @param cap the maximum number of items the cache can hold
     */
    public MRUCache(int cap) {
        setCapacity(cap);
    }

    /**
     * Sets the capacity of the cache and evicts items if the new capacity
     * is less than the current number of items.
     *
     * @param newCapacity the new capacity to set
     */
    private void setCapacity(int newCapacity) {
        checkCapacity(newCapacity);
        while (data.size() > newCapacity) {
            Entry<K, V> evicted = evict();
            data.remove(evicted.getKey());
        }
        this.cap = newCapacity;
    }

    /**
     * Checks if the specified capacity is valid.
     *
     * @param capacity the capacity to check
     * @throws IllegalArgumentException if the capacity is less than or equal to zero
     */
    private void checkCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0!");
        }
    }

    /**
     * Evicts the most recently used entry from the cache.
     *
     * @return the evicted entry
     * @throws RuntimeException if the cache is empty
     */
    private Entry<K, V> evict() {
        if (head == null) {
            throw new RuntimeException("Cache cannot be empty!");
        }
        final Entry<K, V> evicted = this.tail;
        tail = evicted.getPreEntry();
        if (tail != null) {
            tail.setNextEntry(null);
        }
        evicted.setNextEntry(null);
        return evicted;
    }

    /**
     * Retrieves the value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or null if the key does not exist
     */
    public V get(K key) {
        if (!data.containsKey(key)) {
            return null;
        }
        final Entry<K, V> entry = data.get(key);
        moveEntryToLast(entry);
        return entry.getValue();
    }

    /**
     * Associates the specified value with the specified key in the cache.
     * If the key already exists, its value is updated and the entry is moved to the most recently used position.
     * If the cache is full, the most recently used entry is evicted before adding the new entry.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    public void put(K key, V value) {
        if (data.containsKey(key)) {
            final Entry<K, V> existingEntry = data.get(key);
            existingEntry.setValue(value);
            moveEntryToLast(existingEntry);
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
     * Adds a new entry to the cache and updates the head and tail pointers accordingly.
     *
     * @param newEntry the new entry to be added
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

    /**
     * Moves the specified entry to the most recently used position in the cache.
     *
     * @param entry the entry to be moved
     */
    private void moveEntryToLast(Entry<K, V> entry) {
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
     * A nested class representing an entry in the cache, which holds a key-value pair
     * and references to the previous and next entries in the linked list structure.
     *
     * @param <I> the type of the key
     * @param <J> the type of the value
     */
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
