package com.thealgorithms.datastructures.queues;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PriorityQueuesTest {

    @Test
    void testPQInsertion() {
        PriorityQueue myQueue = new PriorityQueue(4);
        myQueue.insert(2);
        Assertions.assertEquals(myQueue.peek(), 2);

        myQueue.insert(5);
        myQueue.insert(3);
        Assertions.assertEquals(myQueue.peek(), 5);

        myQueue.insert(10);
        Assertions.assertEquals(myQueue.peek(), 10);
    }

    @Test
    void testPQDeletion() {
        PriorityQueue myQueue = new PriorityQueue(4);
        myQueue.insert(2);
        myQueue.insert(5);
        myQueue.insert(3);
        myQueue.insert(10);

        myQueue.remove();
        Assertions.assertEquals(myQueue.peek(), 5);
        myQueue.remove();
        myQueue.remove();
        Assertions.assertEquals(myQueue.peek(), 2);
    }

    @Test
    void testPQExtra() {
        PriorityQueue myQueue = new PriorityQueue(4);
        Assertions.assertEquals(myQueue.isEmpty(), true);
        Assertions.assertEquals(myQueue.isFull(), false);
        myQueue.insert(2);
        myQueue.insert(5);
        Assertions.assertEquals(myQueue.isFull(), false);
        myQueue.insert(3);
        myQueue.insert(10);
        Assertions.assertEquals(myQueue.isEmpty(), false);
        Assertions.assertEquals(myQueue.isFull(), true);

        myQueue.remove();
        Assertions.assertEquals(myQueue.getSize(), 3);
        Assertions.assertEquals(myQueue.peek(), 5);
        myQueue.remove();
        myQueue.remove();
        Assertions.assertEquals(myQueue.peek(), 2);
        Assertions.assertEquals(myQueue.getSize(), 1);
    }
}
