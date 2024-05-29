package com.thealgorithms.datastructures.crdt;

import java.util.HashMap;
import java.util.Map;

/**
 * PN-Counter (Positive-Negative Counter) is a state-based CRDT (Conflict-free Replicated Data Type)
 * designed for tracking counts with both increments and decrements in a distributed and concurrent environment.
 * It combines two G-Counters, one for increments (P) and one for decrements (N).
 * The total count is obtained by subtracting the value of the decrement counter from the increment counter.
 * This implementation supports incrementing, decrementing, querying the total count,
 * comparing with other PN-Counters, and merging with another PN-Counter
 * to compute the element-wise maximum for both increment and decrement counters.
 * (https://en.wikipedia.org/wiki/Conflict-free_replicated_data_type)
 *
 * @author itakurah (Niklas Hoefflin) (https://github.com/itakurah)
 */

class PNCounter {
    private final Map<Integer, Integer> pCounter;
    private final Map<Integer, Integer> nCounter;
    private final int myId;
    private final int n;

    /**
     * Constructs a PN-Counter for a cluster of n nodes.
     *
     * @param myId The identifier of the current node.
     * @param n    The number of nodes in the cluster.
     */
    PNCounter(int myId, int n) {
        this.myId = myId;
        this.n = n;
        this.pCounter = new HashMap<>();
        this.nCounter = new HashMap<>();

        for (int i = 0; i < n; i++) {
            pCounter.put(i, 0);
            nCounter.put(i, 0);
        }
    }

    /**
     * Increments the increment counter for the current node.
     */
    public void increment() {
        pCounter.put(myId, pCounter.get(myId) + 1);
    }

    /**
     * Increments the decrement counter for the current node.
     */
    public void decrement() {
        nCounter.put(myId, nCounter.get(myId) + 1);
    }

    /**
     * Gets the total value of the counter by subtracting the decrement counter from the increment counter.
     *
     * @return The total value of the counter.
     */
    public int value() {
        int sumP = pCounter.values().stream().mapToInt(Integer::intValue).sum();
        int sumN = nCounter.values().stream().mapToInt(Integer::intValue).sum();
        return sumP - sumN;
    }

    /**
     * Compares the state of this PN-Counter with another PN-Counter.
     *
     * @param other The other PN-Counter to compare with.
     * @return True if the state of this PN-Counter is less than or equal to the state of the other PN-Counter.
     */
    public boolean compare(PNCounter other) {
        if (this.n != other.n) {
            throw new IllegalArgumentException("Cannot compare PN-Counters with different number of nodes");
        }
        for (int i = 0; i < n; i++) {
            if (this.pCounter.get(i) > other.pCounter.get(i) && this.nCounter.get(i) > other.nCounter.get(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Merges the state of this PN-Counter with another PN-Counter.
     *
     * @param other The other PN-Counter to merge with.
     */
    public void merge(PNCounter other) {
        if (this.n != other.n) {
            throw new IllegalArgumentException("Cannot merge PN-Counters with different number of nodes");
        }
        for (int i = 0; i < n; i++) {
            this.pCounter.put(i, Math.max(this.pCounter.get(i), other.pCounter.get(i)));
            this.nCounter.put(i, Math.max(this.nCounter.get(i), other.nCounter.get(i)));
        }
    }
}
