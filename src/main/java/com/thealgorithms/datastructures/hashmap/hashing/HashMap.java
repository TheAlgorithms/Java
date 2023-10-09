import java.util.*;

package com.thealgorithms.datastructures.hashmap.hashing;

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
    public void insert(K key, V value) {
        int hash = hashing(key);
        LinkedList<Entry<K, V>> bucket = buckets.get(hash);
        bucket.add(new Entry<>(key, value));
    }

    /**
     * Deletes a key-value pair from the hash map.
     *
     * @param key The key to be deleted.
     */
    public void delete(K key) {
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
    public void display() {
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

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        hashMap.insert("one", 1);
        hashMap.insert("two", 2);
        hashMap.insert("three", 3);
        hashMap.delete("two");
        hashMap.display();
    }
}
