package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.concurrent.locks.ReentrantLock;

/**
 * A thread-safe implementation of a HashMap using separate chaining with linked lists
 * and ReentrantLocks for concurrency control.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
@SuppressWarnings("rawtypes")
public class ConcurrentHashMap<K, V> {
    private final int hashSize;
    private final Bucket<K, V>[] buckets;
    private final ReentrantLock[] locks;

    /**
     * Constructs a ConcurrentHashMap with the specified hash size.
     *
     * @param hashSize the number of buckets in the hash map
     */
    @SuppressWarnings("unchecked")
    public ConcurrentHashMap(int hashSize) {
        this.hashSize = hashSize;
        this.buckets = new Bucket[hashSize];
        this.locks = new ReentrantLock[hashSize];
        for (int i = 0; i < hashSize; i++) {
            buckets[i] = new Bucket<>();
            locks[i] = new ReentrantLock();
        }
    }

    /**
     * Computes the hash code for the specified key.
     * Null keys are hashed to bucket 0.
     *
     * @param key the key for which the hash code is to be computed
     * @return the hash code corresponding to the key
     */
    private int computeHash(K key) {
        if (key == null) {
            return 0; // Use a special bucket (e.g., bucket 0) for null keys
        }
        int hash = key.hashCode() % hashSize;
        return hash < 0 ? hash + hashSize : hash;
    }

    /**
     * Inserts the specified key-value pair into the hash map.
     * If the key already exists, the value is updated.
     *
     * @param key   the key to be inserted
     * @param value the value to be associated with the key
     */
    public void put(K key, V value) {
        int hash = computeHash(key);
        locks[hash].lock();
        try {
            buckets[hash].put(key, value);
        } finally {
            locks[hash].unlock();
        }
    }

    /**
     * Retrieves the value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or null if the key does not exist
     */
    public V get(K key) {
        int hash = computeHash(key);
        locks[hash].lock();
        try {
            return buckets[hash].get(key);
        } finally {
            locks[hash].unlock();
        }
    }

    /**
     * Removes the key-value pair associated with the specified key from the hash map.
     *
     * @param key the key whose key-value pair is to be removed
     */
    public void remove(K key) {
        int hash = computeHash(key);
        locks[hash].lock();
        try {
            buckets[hash].remove(key);
        } finally {
            locks[hash].unlock();
        }
    }

    /**
     * Checks if the hash map contains the specified key.
     *
     * @param key the key to check
     * @return true if the key exists, false otherwise
     */
    public boolean containsKey(K key) {
        int hash = computeHash(key);
        locks[hash].lock();
        try {
            return buckets[hash].containsKey(key);
        } finally {
            locks[hash].unlock();
        }
    }

    /**
     * A nested static class representing a bucket in the hash map.
     * Each bucket uses a linked list to store key-value pairs.
     *
     * @param <K> the type of keys maintained by this bucket
     * @param <V> the type of mapped values
     */
    private static class Bucket<K, V> {
        private Node<K, V> head;

        public void put(K key, V value) {
            Node<K, V> node = findNode(key);
            if (node != null) {
                node.value = value;
            } else {
                Node<K, V> newNode = new Node<>(key, value);
                newNode.next = head;
                head = newNode;
            }
        }

        public V get(K key) {
            Node<K, V> node = findNode(key);
            return node != null ? node.value : null;
        }

        public void remove(K key) {
            if (head == null) {
                return;
            }
            if ((key == null && head.key == null) || (head.key != null && head.key.equals(key))) {
                head = head.next;
                return;
            }
            Node<K, V> current = head;
            while (current.next != null) {
                if ((key == null && current.next.key == null) || (current.next.key != null && current.next.key.equals(key))) {
                    current.next = current.next.next;
                    return;
                }
                current = current.next;
            }
        }

        public boolean containsKey(K key) {
            return findNode(key) != null;
        }

        private Node<K, V> findNode(K key) {
            Node<K, V> current = head;
            while (current != null) {
                if ((key == null && current.key == null) || (current.key != null && current.key.equals(key))) {
                    return current;
                }
                current = current.next;
            }
            return null;
        }
    }

    /**
     * A nested static class representing a node in the linked list.
     *
     * @param <K> the type of key maintained by this node
     * @param <V> the type of value maintained by this node
     */
    private static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
