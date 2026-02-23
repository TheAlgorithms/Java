package com.thealgorithms.datastructures.queues;
import java.util.Queue;
public final class ReverseQueueRecursion {
    private ReverseQueueRecursion() {
    }
    public static <T> void reverseQueue(final Queue<T> queue) {
        if (queue == null || queue.isEmpty()) {
            return;
        }
        final T front = queue.poll();
        reverseQueue(queue);
        queue.add(front);
    }
}
