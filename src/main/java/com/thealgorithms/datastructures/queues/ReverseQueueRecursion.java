package com.thealgorithms.datastructures.queues;

import java.util.Queue;

/**
 * Reverse a queue using recursion.
 */
public final class ReverseQueueRecursion {

    private ReverseQueueRecursion() {
        // private constructor to prevent instantiation
    }

    /**
     * Reverses the given queue recursively.
     *
     * @param queue the queue to reverse
     * @param <T> type of elements in the queue
     */
    public static <T> void reverseQueue(Queue<T> queue) {
        if (queue.isEmpty()) return;
        T front = queue.poll();
        reverseQueue(queue);
        queue.add(front);
    }
}
