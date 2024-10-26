package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A generic implementation of a hash map using an array list of linked lists for collision resolution.
 * This class allows storage of key-value pairs with average-case constant time complexity for insertion,
 * deletion, and retrieval operations.
 *
 * <p>
 * The hash map uses separate chaining to handle collisions. Each bucket in the hash map is represented
 * by a linked list that holds nodes containing key-value pairs. When multiple keys hash to the same index,
 * they are stored in the same linked list.
 * </p>
 *
 * <p>
 * The hash map automatically resizes itself when the load factor exceeds 0.5. The load factor is defined
 * as the ratio of the number of entries to the number of buckets. When resizing occurs, all existing entries
 * are rehashed and inserted into the new buckets.
 * </p>
 *
 * @param <K> the type of keys maintained by this hash map
 * @param <V> the type of mapped values
 */
public class GenericHashMapUsingArrayList<K, V> {

    private ArrayList<LinkedList<Node>> buckets; // Array list of buckets (linked lists)
    private int size; // Number of key-value pairs in the hash map

    /**
     * Constructs a new empty hash map with an initial capacity of 10 buckets.
     */
    public GenericHashMapUsingArrayList() {
        buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new LinkedList<>());
        }
        size = 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    public void put(K key, V value) {
        int hash = Math.abs(key.hashCode() % buckets.size());
        LinkedList<Node> nodes = buckets.get(hash);

        for (Node node : nodes) {
            if (node.key.equals(key)) {
                node.val = value;
                return;
            }
        }

        nodes.add(new Node(key, value));
        size++;

        // Load factor threshold for resizing
        float loadFactorThreshold = 0.5f;
        if ((float) size / buckets.size() > loadFactorThreshold) {
            reHash();
        }
    }

    /**
     * Resizes the hash map by doubling the number of buckets and rehashing existing entries.
     */
    private void reHash() {
        ArrayList<LinkedList<Node>> oldBuckets = buckets;
        buckets = new ArrayList<>();
        size = 0;
        for (int i = 0; i < oldBuckets.size() * 2; i++) {
            buckets.add(new LinkedList<>());
        }
        for (LinkedList<Node> nodes : oldBuckets) {
            for (Node node : nodes) {
                put(node.key, node.val);
            }
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or null if no mapping exists
     */
    public V get(K key) {
        int hash = Math.abs(key.hashCode() % buckets.size());
        LinkedList<Node> nodes = buckets.get(hash);
        for (Node node : nodes) {
            if (node.key.equals(key)) {
                return node.val;
            }
        }
        return null;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key the key whose mapping is to be removed from the map
     */
    public void remove(K key) {
        int hash = Math.abs(key.hashCode() % buckets.size());
        LinkedList<Node> nodes = buckets.get(hash);

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
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    public boolean containsKey(K key) {
        return get(key) != null;
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
     * Returns a string representation of the map, containing all key-value pairs.
     *
     * @return a string representation of the map
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (LinkedList<Node> nodes : buckets) {
            for (Node node : nodes) {
                builder.append(node.key);
                builder.append(" : ");
                builder.append(node.val);
                builder.append(", ");
            }
        }
        // Remove trailing comma and space if there are any elements
        if (builder.length() > 1) {
            builder.setLength(builder.length() - 2);
        }
        builder.append("}");
        return builder.toString();
    }

    /**
     * A private inner class representing a key-value pair (node) in the hash map.
     */
    private class Node {
        K key;
        V val;

        /**
         * Constructs a new Node with the specified key and value.
         *
         * @param key the key of the key-value pair
         * @param val the value of the key-value pair
         */
        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
}
