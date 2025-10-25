package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.LinkedList;

/**
 * A generic implementation of a hash map using an array of linked lists for collision resolution.
 * This class provides a way to store key-value pairs efficiently, allowing for average-case
 * constant time complexity for insertion, deletion, and retrieval operations.
 *
 * <p>
 * The hash map uses separate chaining for collision resolution. Each bucket in the hash map is a
 * linked list that stores nodes containing key-value pairs. When a collision occurs (i.e., when
 * two keys hash to the same index), the new key-value pair is simply added to the corresponding
 * linked list.
 * </p>
 *
 * <p>
 * The hash map automatically resizes itself when the load factor exceeds 0.75. The load factor is
 * defined as the ratio of the number of entries to the number of buckets. When resizing occurs,
 * all existing entries are rehashed and inserted into the new buckets.
 * </p>
 *
 * @param <K> the type of keys maintained by this hash map
 * @param <V> the type of mapped values
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class GenericHashMapUsingArray<K, V> {

    private int size; // Total number of key-value pairs
    private LinkedList<Node>[] buckets; // Array of linked lists (buckets) for storing entries

    /**
     * Constructs a new empty hash map with an initial capacity of 16.
     */
    public GenericHashMapUsingArray() {
        initBuckets(16);
        size = 0;
    }

    /**
     * Initializes the buckets for the hash map with the specified number of buckets.
     *
     * @param n the number of buckets to initialize
     */
    private void initBuckets(int n) {
        buckets = new LinkedList[n];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    public void put(K key, V value) {
        int bucketIndex = hashFunction(key);
        LinkedList<Node> nodes = buckets[bucketIndex];
        // Update existing key's value if present
        for (Node node : nodes) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        // Insert new key-value pair
        nodes.add(new Node(key, value));
        size++;

        // Check if rehashing is needed
        // Load factor threshold for resizing
        float loadFactorThreshold = 0.75f;
        if ((float) size / buckets.length > loadFactorThreshold) {
            reHash();
        }
    }

    /**
     * Returns the index of the bucket in which the key would be stored.
     *
     * @param key the key whose bucket index is to be computed
     * @return the bucket index
     */
    private int hashFunction(K key) {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    /**
     * Rehashes the map by doubling the number of buckets and re-inserting all entries.
     */
    private void reHash() {
        LinkedList<Node>[] oldBuckets = buckets;
        initBuckets(oldBuckets.length * 2);
        this.size = 0;

        for (LinkedList<Node> nodes : oldBuckets) {
            for (Node node : nodes) {
                put(node.key, node.value);
            }
        }
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key the key whose mapping is to be removed from the map
     */
    public void remove(K key) {
        int bucketIndex = hashFunction(key);
        LinkedList<Node> nodes = buckets[bucketIndex];

        Node target = null;
        for (Node node : nodes) {
            if (node.key.equals(key)) {
                target = node;
                break;
            }
        }

        if (target != null) {
            nodes.remove(target);
            size--;
        }
    }

    /**
     * Returns the number of key-value pairs in this map.
     *
     * @return the number of key-value pairs
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or null if no mapping exists
     */
    public V get(K key) {
        int bucketIndex = hashFunction(key);
        LinkedList<Node> nodes = buckets[bucketIndex];
        for (Node node : nodes) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (LinkedList<Node> nodes : buckets) {
            for (Node node : nodes) {
                builder.append(node.key);
                builder.append(" : ");
                builder.append(node.value);
                builder.append(", ");
            }
        }
        // Remove trailing comma and space
        if (builder.length() > 1) {
            builder.setLength(builder.length() - 2);
        }
        builder.append("}");
        return builder.toString();
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * A private class representing a key-value pair (node) in the hash map.
     */
    public class Node {
        K key;
        V value;

        /**
         * Constructs a new Node with the specified key and value.
         *
         * @param key the key of the key-value pair
         * @param value the value of the key-value pair
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
