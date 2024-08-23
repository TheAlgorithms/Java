package com.thealgorithms.datastructures.caches;

import java.util.HashMap;
import java.util.Map;

/**
 * Java program for LFU Cache (https://en.wikipedia.org/wiki/Least_frequently_used)
 * @author Akshay Dubey (https://github.com/itsAkshayDubey)
 */
public class LFUCache<K, V> {

    private class Node {
        private final K key;
        private V value;
        private int frequency;
        private Node previous;
        private Node next;

        Node(K key, V value, int frequency) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }
    }

    private Node head;
    private Node tail;
    private final Map<K, Node> cache;
    private final int capacity;
    private static final int DEFAULT_CAPACITY = 100;

    public LFUCache() {
        this(DEFAULT_CAPACITY);
    }

    public LFUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.capacity = capacity;
        this.cache = new HashMap<>();
    }

    /**
     * Retrieves the value for the given key from the cache. Increases the frequency of the node.
     *
     * @param key The key to look up.
     * @return The value associated with the key, or null if the key is not present.
     */
    public V get(K key) {
        Node node = cache.get(key);
        if (node == null) {
            return null;
        }
        removeNode(node);
        node.frequency += 1;
        addNodeWithUpdatedFrequency(node);
        return node.value;
    }

    /**
     * Adds or updates a key-value pair in the cache. If the cache is full, the least frequently used item is evicted.
     *
     * @param key   The key to insert or update.
     * @param value The value to insert or update.
     */
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            node.frequency += 1;
            removeNode(node);
            addNodeWithUpdatedFrequency(node);
        } else {
            if (cache.size() >= capacity) {
                cache.remove(this.head.key);
                removeNode(head);
            }
            Node node = new Node(key, value, 1);
            addNodeWithUpdatedFrequency(node);
            cache.put(key, node);
        }
    }

    /**
     * Adds a node to the linked list in the correct position based on its frequency.
     *
     * @param node The node to add.
     */
    private void addNodeWithUpdatedFrequency(Node node) {
        if (tail != null && head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.frequency > node.frequency) {
                    if (temp == head) {
                        node.next = temp;
                        temp.previous = node;
                        this.head = node;
                        break;
                    } else {
                        node.next = temp;
                        node.previous = temp.previous;
                        temp.previous.next = node;
                        temp.previous = node;
                        break;
                    }
                } else {
                    temp = temp.next;
                    if (temp == null) {
                        tail.next = node;
                        node.previous = tail;
                        node.next = null;
                        tail = node;
                        break;
                    }
                }
            }
        } else {
            tail = node;
            head = tail;
        }
    }

    /**
     * Removes a node from the linked list.
     *
     * @param node The node to remove.
     */
    private void removeNode(Node node) {
        if (node.previous != null) {
            node.previous.next = node.next;
        } else {
            this.head = node.next;
        }

        if (node.next != null) {
            node.next.previous = node.previous;
        } else {
            this.tail = node.previous;
        }
    }
}
