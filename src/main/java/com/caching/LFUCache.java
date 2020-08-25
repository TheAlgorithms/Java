package com.caching;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * Your LFUCache object can be instantiated and called as such: LFUCache
 * lfuCache = new LFUCache(capacity); lfuCache.put(key,value); int param_1 =
 * lfuCache.get(key);
 */
class LFUCache<T> {
    // internal Node to store cache element
    private class Node {
        int key;
        T value;
        int freq;
        Node next;
        Node pre;

        public Node(int key, T value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
            next = pre = null;
        }

        public String toString() {
            return " Key: " + key + "Value: " + value + "Freq: " + freq;
        }

    }

    // internal Doubly Linked List to store cache nodes
    private class DLL {
        Node head;
        Node tail;
        int len;

        public DLL() {
            head = new Node(-1, null, -1);
            tail = new Node(-1, null, -1);
            head.next = tail;
            tail.pre = head;
            len = 0;
        }

        public void addToHead(Node node) {
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
            node.pre = head;
            len++;
        }

        public void deleteNode(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            len--;
        }

    }

    private int capacity;
    private int size;
    private TreeMap<Integer, DLL> freq;
    private HashMap<Integer, Node> map;

    /**
     * instantiates LFUCache with fixed capacity
     * 
     * @param capacity The capacity of the cache. Once the cache reaches capacity,
     *                 new elements will replace old elements in LFU manner
     */
    public LFUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        freq = new TreeMap<>();
        map = new HashMap<>();
        System.out.println("LFUCache initialised with capacity: " + capacity);
    }

    /**
     * To get the cached value for given key
     * 
     * @param key The key (int) of the expected value
     * @return corresponding value for input key
     * @throws NoSuchElementException if key is absent
     */
    public T get(int key) {
        // Cache hit condition
        if (map.containsKey(key)) {
            Node node = map.get(key);
            System.out.println("Returning value from cache:" + node.toString());
            DLL dll = freq.get(node.freq);
            dll.deleteNode(node);
            if (dll.len == 0)
                freq.remove(node.freq);
            node.freq += 1;
            dll = freq.computeIfAbsent(node.freq, k -> new DLL());
            dll.addToHead(node);
            return node.value;
        }
        // Cache miss condition
        throw new NoSuchElementException("No element for key: " + key);
    }

    /**
     * To put a value in LFU cache with corresponding key
     * 
     * @param key   The key (int)
     * @param value The value to be cached
     */
    public void put(int key, T value) {
        if (capacity == 0) {
            System.out.println("Cache set to 0 capacity. No element will be cached");
            return;
        }
        if (map.containsKey(key)) {
            System.out.println("Key " + key + " already present in cache.Value will be replaced");
            Node node = map.get(key);
            node.value = value;
            DLL dll = freq.get(node.freq);
            dll.deleteNode(node);
            if (dll.len == 0)
                freq.remove(node.freq);
            node.freq += 1;
            dll = freq.computeIfAbsent(node.freq, k -> new DLL());
            dll.addToHead(node);
        } else {
            System.out.println("Adding new key " + key + " to cache");
            Node node = new Node(key, value, 1);
            map.put(key, node);

            if (size < capacity) {
                size++;
                DLL dll = freq.computeIfAbsent(1, k -> new DLL());
                dll.addToHead(node);
            } else {
                System.out.println("Cache at peak capacity.Old values will be removed in LFU fashion");
                Integer lowest = freq.firstKey();
                DLL dll = freq.get(lowest);
                map.remove(dll.tail.pre.key);
                System.out.println("Value removed:" + dll.tail.pre.value.toString());
                dll.deleteNode(dll.tail.pre);
                if (dll.len == 0 && lowest != 1)
                    freq.remove(lowest);
                DLL freqOne = freq.computeIfAbsent(1, k -> new DLL());
                freqOne.addToHead(node);
            }
        }

    }

}