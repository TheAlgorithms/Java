
package com.thealgorithms.datastructures.hashset;

/**
 * Example usage:
 * <pre>
 * HashSet set = new HashSet();
 * set.add(5);
 * set.add(10);
 * System.out.println(set.contains(5)); // true
 * set.remove(5);
 * System.out.println(set.contains(5)); // false
 * </pre>
 */

import java.util.LinkedList;

/**
 * A simple implementation of a HashSet for integers using separate chaining.
 *
 * See: https://en.wikipedia.org/wiki/Hash_table
 */
public class HashSet {
    private static final int INITIAL_CAPACITY = 16;
    private LinkedList<Integer>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public HashSet() {
        buckets = (LinkedList<Integer>[]) new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            buckets[i] = new LinkedList<Integer>();
        }
        size = 0;
    }

    private int hash(int key) {
        return Math.abs(key) % buckets.length;
    }

    public void add(int key) {
        int idx = hash(key);
        if (!buckets[idx].contains(key)) {
            buckets[idx].add(key);
            size++;
        }
    }

    public boolean contains(int key) {
        int idx = hash(key);
        return buckets[idx].contains(key);
    }

    public void remove(int key) {
        int idx = hash(key);
        if (buckets[idx].remove((Integer) key)) {
            size--;
        }
    }

    public int size() {
        return size;
    }
}

