package com.thealgorithms.datastructures.caches;

import java.util.HashMap;
import java.util.Map;

/**
 * Most recently used (MRU)
 * <p>
 * In contrast to Least Recently Used (LRU), MRU discards the most recently used
 * items first.
 * https://en.wikipedia.org/wiki/Cache_replacement_policies#Most_recently_used_(MRU)
 *
 * @param <K> key type
 * @param <V> value type
 */
public class MRUCache<K, V> {

    private final Map<K, Entry<K, V>> data = new HashMap<>();
    private Entry<K, V> head;
    private Entry<K, V> tail;
    private int cap;
    private static final int DEFAULT_CAP = 100;

    public MRUCache() {
        setCapacity(DEFAULT_CAP);
    }

    private void setCapacity(int newCapacity) {
        checkCapacity(newCapacity);
        for (int i = data.size(); i > newCapacity; i--) {
            Entry<K, V> evicted = evict();
            data.remove(evicted.getKey());
        }
        this.cap = newCapacity;
    }

    private void checkCapacity(int capacity) {
        if (capacity <= 0) {
            throw new RuntimeException("capacity must greater than 0!");
        }
    }

    private Entry<K, V> evict() {
        if (head == null) {
            throw new RuntimeException("cache cannot be empty!");
        }
        final Entry<K, V> evicted = this.tail;
        tail = evicted.getPreEntry();
        tail.setNextEntry(null);
        evicted.setNextEntry(null);
        return evicted;
    }

    public MRUCache(int cap) {
        setCapacity(cap);
    }

    public V get(K key) {
        if (!data.containsKey(key)) {
            return null;
        }
        final Entry<K, V> entry = data.get(key);
        moveEntryToLast(entry);
        return entry.getValue();
    }

    public void put(K key, V value) {
        if (data.containsKey(key)) {
            final Entry<K, V> exitingEntry = data.get(key);
            exitingEntry.setValue(value);
            moveEntryToLast(exitingEntry);
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

    static final class Entry<I, J> {

        private Entry<I, J> preEntry;
        private Entry<I, J> nextEntry;
        private I key;
        private J value;

        public Entry() {
        }

        public Entry(Entry<I, J> preEntry, Entry<I, J> nextEntry, I key, J value) {
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
