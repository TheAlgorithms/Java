package com.thealgorithms.datastructures.queues;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LinkedQueueTest {
    @Test
    public void testQue() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        for (int i = 1; i < 5; i++) queue.enqueue(i);

        assertEquals(queue.peekRear(), 4);
        assertEquals(queue.peek(2), 2);

        assertEquals(queue.peek(4), 4);

        final int[] element = {1};

        // iterates over all the elements present
        // as in the form of nodes
        queue.forEach(integer -> {
            if (element[0]++ != integer) throw new AssertionError();
        });
    }
}