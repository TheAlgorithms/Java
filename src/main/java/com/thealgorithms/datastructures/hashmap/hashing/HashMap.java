package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.*;

/**
 * A simple hash map implementation using separate chaining.
 *
 * @param <K> The key type.
 * @param <V> The value type.
 */
public class HashMap<K, V> {

    private final int hsize;
    private final List<LinkedList<Entry<K, V>>> buckets;

    /**
     * Creates a new hash map with the specified size.
     *
     * @param hsize The size of the hash map.
     */
    public HashMap(int hsize) {
        this.hsize = hsize;
        buckets = new ArrayList<>(hsize);
        for (int i = 0; i < hsize; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    /**
     * Hashes a key to determine the bucket index.
     *
     * @param key The key to be hashed.
     * @return The index of the bucket where the key belongs.
     */
    private int hashing(K key) {
        int hashCode = key.hashCode();
        int hash = hashCode % hsize;
        if (hash < 0) {
            hash += hsize;
        }
        return hash;
    }

    /**
     * Inserts a key-value pair into the hash map.
     *
     * @param key   The key to be inserted.
     * @param value The value associated with the key.
     */
    public void insertHash(K key, V value) {
        int hash = hashing(key);
        LinkedList<Entry<K, V>> bucket = buckets.get(hash);
        // Add a new entry without checking for duplicates
        bucket.add(new Entry<>(key, value));
    }

    /**
     * Deletes a key-value pair from the hash map.
     *
     * @param key The key to be deleted.
     */
    public void deleteHash(K key) {
        int hash = hashing(key);
        LinkedList<Entry<K, V>> bucket = buckets.get(hash);
        Iterator<Entry<K, V>> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if (entry.key.equals(key)) {
                iterator.remove();
                return;
            }
        }
    }

    /**
     * Displays the contents of the hash map.
     */
    public void displayHashtable() {
        for (int i = 0; i < hsize; i++) {
            System.out.printf("Bucket %d: %s%n", i, buckets.get(i));
        }
    }

    /**
     * Represents a key-value pair entry in the hash map.
     *
     * @param <K> The key type.
     * @param <V> The value type.
     */
    private static class Entry<K, V> {
        private final K key;
        private final V value;

        /**
         * Constructs a new Entry.
         *
         * @param key   The key.
         * @param value The value.
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    /**
     * Main method for testing the HashMap implementation.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        hashMap.insertHash("one", 1);
        hashMap.insertHash("two", 2);
        hashMap.insertHash("three", 3);
        hashMap.deleteHash("two");
        hashMap.displayHashtable();
    }
}

