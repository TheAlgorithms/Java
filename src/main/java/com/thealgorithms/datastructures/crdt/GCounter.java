package com.thealgorithms.datastructures.crdt;

import java.util.HashMap;
import java.util.Map;

/**
 * G-Counter (Grow-only Counter) is a state-based CRDT (Conflict-free Replicated Data Type)
 * designed for tracking counts in a distributed and concurrent environment.
 * Each process maintains its own counter, allowing only increments. The total count
 * is obtained by summing individual process counts.
 * This implementation supports incrementing, querying the total count,
 * comparing with other G-Counters, and merging with another G-Counter
 * to compute the element-wise maximum.
 * (https://en.wikipedia.org/wiki/Conflict-free_replicated_data_type)
 *
 * @author itakurah (https://github.com/itakurah)
 */

class GCounter {
    private final Map<Integer, Integer> counterMap;
    private final int myId;
    private final int n;

    /**
     * Constructs a G-Counter for a cluster of n nodes.
     *
     * @param n The number of nodes in the cluster.
     */
    GCounter(int myId, int n) {
        this.myId = myId;
        this.n = n;
        this.counterMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            counterMap.put(i, 0);
        }
    }

    /**
     * Increments the counter for the current node.
     */
    public void increment() {
        counterMap.put(myId, counterMap.get(myId) + 1);
    }

    /**
     * Gets the total value of the counter by summing up values from all nodes.
     *
     * @return The total value of the counter.
     */
    public int value() {
        int sum = 0;
        for (int v : counterMap.values()) {
            sum += v;
        }
        return sum;
    }

    /**
     * Compares the state of this G-Counter with another G-Counter.
     *
     * @param other The other G-Counter to compare with.
     * @return True if the state of this G-Counter is less than or equal to the state of the other G-Counter.
     */
    public boolean compare(GCounter other) {
        for (int i = 0; i < n; i++) {
            if (this.counterMap.get(i) > other.counterMap.get(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Merges the state of this G-Counter with another G-Counter.
     *
     * @param other The other G-Counter to merge with.
     */
    public void merge(GCounter other) {
        for (int i = 0; i < n; i++) {
            this.counterMap.put(i, Math.max(this.counterMap.get(i), other.counterMap.get(i)));
        }
    }
}
