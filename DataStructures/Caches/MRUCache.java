package DataStructures.Caches;

import java.util.HashMap;
import java.util.Map;

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
