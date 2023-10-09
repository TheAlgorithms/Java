package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.LinkedList;

// implementation of generic hashmaps using array of Linked Lists

public class GenericHashMapUsingArray<K, V> {

    private int size; // n (total number of key-value pairs)
    private LinkedList<Node>[] buckets; // N = buckets.length
    private float lf = 0.75f;

    public GenericHashMapUsingArray() {
        initBuckets(16);
        size = 0;
    }

    // load factor = 0.75 means if we need to add 100 items and we have added
    // 75, then adding 76th item it will double the size, copy all elements
    // & then add 76th item.

    private void initBuckets(int N) {
        buckets = new LinkedList[N];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void put(K key, V value) {
        int bucketIndex = hashFunction(key);
        LinkedList<Node> nodes = buckets[bucketIndex];
        for (Node node : nodes) { // if key present => update
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        // key is not present => insert
        nodes.add(new Node(key, value));
        size++;

        if ((float) size / buckets.length > lf) {
            reHash();
        }
    }

    // tells which bucket to go to
    private int hashFunction(K key) {
        int hc = key.hashCode();
        return Math.abs(hc) % buckets.length;
    }

    private void reHash() {
        System.out.println("Rehashing!");
        LinkedList<Node>[] old = buckets;
        initBuckets(old.length * 2);
        this.size = 0;

        for (LinkedList<Node> nodes : old) {
            for (Node node : nodes) {
                put(node.key, node.value);
            }
        }
    }

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
        nodes.remove(target);
        size--;
    }

    public int size() {
        return this.size;
    }

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
        builder.append("}");
        return builder.toString();
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public class Node {

        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
