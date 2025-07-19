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

    @Test
    void testInsertUntilFull() {
        PriorityQueue pq = new PriorityQueue(3);
        pq.insert(1);
        pq.insert(4);
        pq.insert(2);
        Assertions.assertTrue(pq.isFull());
        Assertions.assertEquals(4, pq.peek());
    }

    @Test
    void testRemoveFromEmpty() {
        PriorityQueue pq = new PriorityQueue(3);
        Assertions.assertThrows(RuntimeException.class, pq::remove);
    }

    @Test
    void testInsertDuplicateValues() {
        PriorityQueue pq = new PriorityQueue(5);
        pq.insert(5);
        pq.insert(5);
        pq.insert(3);
        Assertions.assertEquals(5, pq.peek());
        pq.remove();
        Assertions.assertEquals(5, pq.peek());
        pq.remove();
        Assertions.assertEquals(3, pq.peek());
    }

    @Test
    void testSizeAfterInsertAndRemove() {
        PriorityQueue pq = new PriorityQueue(4);
        Assertions.assertEquals(0, pq.getSize());
        pq.insert(2);
        Assertions.assertEquals(1, pq.getSize());
        pq.insert(10);
        Assertions.assertEquals(2, pq.getSize());
        pq.remove();
        Assertions.assertEquals(1, pq.getSize());
        pq.remove();
        Assertions.assertEquals(0, pq.getSize());
    }

    @Test
    void testInsertAndRemoveAll() {
        PriorityQueue pq = new PriorityQueue(3);
        pq.insert(8);
        pq.insert(1);
        pq.insert(6);
        Assertions.assertTrue(pq.isFull());
        pq.remove();
        pq.remove();
        pq.remove();
        Assertions.assertTrue(pq.isEmpty());
    }
}
