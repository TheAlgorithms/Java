package com.thealgorithms.datastructures.caches;

import java.util.HashMap;
import java.util.Map;

/**
 * Java program for LFU Cache (https://en.wikipedia.org/wiki/Least_frequently_used)
 * @author Akshay Dubey (https://github.com/itsAkshayDubey)
 */
public class LFUCache<K, V> {

    private class Node {

        private K key;
        private V value;
        private int frequency;
        private Node previous;
        private Node next;

        public Node(K key, V value, int frequency) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }
    }

    private Node head;
    private Node tail;
    private Map<K, Node> map = null;
    private Integer capacity;
    private static final int DEFAULT_CAPACITY = 100;

    public LFUCache() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public LFUCache(Integer capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    /**
     * This method returns value present in the cache corresponding to the key passed as parameter
     *
     * @param <K> key for which value is to be retrieved
     * @returns <V> object corresponding to the key passed as parameter, returns null if <K> key is not present in the cache
     */
    public V get(K key) {
        if (this.map.get(key) == null) {
            return null;
        }

        Node node = map.get(key);
        removeNode(node);
        node.frequency += 1;
        addNodeWithUpdatedFrequency(node);

        return node.value;
    }

    /**
     * This method stores <K> key and <V> value in the cache
     *
     * @param <K> key which is to be stored in the cache
     * @param <V> value which is to be stored in the cache
     */
    public void put(K key, V value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            node.frequency += 1;
            removeNode(node);
            addNodeWithUpdatedFrequency(node);
        } else {
            if (map.size() >= capacity) {
                map.remove(this.head.key);
                removeNode(head);
            }
            Node node = new Node(key, value, 1);
            addNodeWithUpdatedFrequency(node);
            map.put(key, node);
        }
    }

    /**
     * This method stores the node in the cache with updated frequency
     *
     * @param Node node which is to be updated in the cache
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
                        node.previous = temp.previous;
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
     * This method removes node from the cache
     *
     * @param Node node which is to be removed in the cache
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
