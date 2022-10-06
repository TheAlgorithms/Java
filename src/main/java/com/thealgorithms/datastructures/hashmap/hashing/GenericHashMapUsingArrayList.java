package com.thealgorithms.datastructures.hashmap.hashing;

import java.util.ArrayList;
import java.util.LinkedList;

public class GenericHashMapUsingArrayList<K, V> {

    ArrayList<LinkedList<Node>> buckets;
    private float lf = 0.5f;
    private int size;

    public GenericHashMapUsingArrayList() {
        buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new LinkedList<>());
        }
        size = 0;
    }

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

        if ((float) size / buckets.size() > lf) {
            reHash();
        }
    }

    private void reHash() {
        ArrayList<LinkedList<Node>> old = buckets;
        buckets = new ArrayList<>();
        size = 0;
        for (int i = 0; i < old.size() * 2; i++) {
            buckets.add(new LinkedList<>());
        }
        for (LinkedList<Node> nodes : buckets) {
            for (Node node : nodes) {
                put(node.key, node.val);
            }
        }
    }

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
        nodes.remove(target);
        size--;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return this.size;
    }

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
        builder.append("}");
        return builder.toString();
    }

    private class Node {

        K key;
        V val;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
}
