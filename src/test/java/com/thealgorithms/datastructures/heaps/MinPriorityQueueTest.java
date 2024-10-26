package com.thealgorithms.datastructures.heaps;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinPriorityQueueTest {

    @Test
    void testInsertAndPeek() {
        MinPriorityQueue queue = new MinPriorityQueue(5);
        queue.insert(10);
        queue.insert(5);
        queue.insert(15);

        Assertions.assertEquals(5, queue.peek(), "The minimum element should be 5.");
    }

    @Test
    void testDelete() {
        MinPriorityQueue queue = new MinPriorityQueue(5);
        queue.insert(10);
        queue.insert(5);
        queue.insert(15);

        Assertions.assertEquals(5, queue.delete(), "The deleted minimum element should be 5.");
        Assertions.assertEquals(10, queue.peek(), "After deletion, the new minimum should be 10.");
    }

    @Test
    void testIsEmpty() {
        MinPriorityQueue queue = new MinPriorityQueue(5);
        Assertions.assertTrue(queue.isEmpty(), "The queue should be empty initially.");

        queue.insert(10);
        Assertions.assertFalse(queue.isEmpty(), "The queue should not be empty after insertion.");
    }

    @Test
    void testIsFull() {
        MinPriorityQueue queue = new MinPriorityQueue(2);
        queue.insert(10);
        queue.insert(5);

        Assertions.assertTrue(queue.isFull(), "The queue should be full after inserting two elements.");
        queue.delete();
        Assertions.assertFalse(queue.isFull(), "The queue should not be full after deletion.");
    }

    @Test
    void testHeapSort() {
        MinPriorityQueue queue = new MinPriorityQueue(5);
        queue.insert(10);
        queue.insert(5);
        queue.insert(15);
        queue.insert(1);
        queue.insert(3);

        // Delete all elements to sort the queue
        int[] sortedArray = new int[5];
        for (int i = 0; i < 5; i++) {
            sortedArray[i] = queue.delete();
        }

        Assertions.assertArrayEquals(new int[] {1, 3, 5, 10, 15}, sortedArray, "The array should be sorted in ascending order.");
    }

    @Test
    void testPeekEmptyQueue() {
        MinPriorityQueue queue = new MinPriorityQueue(5);
        Assertions.assertThrows(IllegalStateException.class, queue::peek, "Should throw an exception when peeking into an empty queue.");
    }

    @Test
    void testDeleteEmptyQueue() {
        MinPriorityQueue queue = new MinPriorityQueue(5);
        Assertions.assertThrows(IllegalStateException.class, queue::delete, "Should throw an exception when deleting from an empty queue.");
    }

    @Test
    void testInsertWhenFull() {
        MinPriorityQueue queue = new MinPriorityQueue(2);
        queue.insert(10);
        queue.insert(5);

        Assertions.assertThrows(IllegalStateException.class, () -> queue.insert(15), "Should throw an exception when inserting into a full queue.");
    }
}
