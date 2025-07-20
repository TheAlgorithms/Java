package com.thealgorithms.datastructures.queues;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueueTest {

    private static final int INITIAL_CAPACITY = 3;
    private Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>(INITIAL_CAPACITY);
    }

    @Test
    void testQueueInsertion() {
        Assertions.assertTrue(queue.insert(1));
        Assertions.assertTrue(queue.insert(2));
        Assertions.assertTrue(queue.insert(3));
        Assertions.assertFalse(queue.insert(4)); // Queue is full

        Assertions.assertEquals(1, queue.peekFront());
        Assertions.assertEquals(3, queue.peekRear());
        Assertions.assertEquals(3, queue.getSize());
    }

    @Test
    void testQueueRemoval() {
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);

        Assertions.assertEquals(1, queue.remove());
        Assertions.assertEquals(2, queue.peekFront());
        Assertions.assertEquals(2, queue.getSize());

        Assertions.assertEquals(2, queue.remove());
        Assertions.assertEquals(3, queue.peekFront());
        Assertions.assertEquals(1, queue.getSize());

        Assertions.assertEquals(3, queue.remove());
        Assertions.assertTrue(queue.isEmpty());

        Assertions.assertThrows(IllegalStateException.class, queue::remove); // Queue is empty
    }

    @Test
    void testPeekFrontAndRear() {
        queue.insert(1);
        queue.insert(2);

        Assertions.assertEquals(1, queue.peekFront());
        Assertions.assertEquals(2, queue.peekRear());

        queue.insert(3);
        Assertions.assertEquals(1, queue.peekFront());
        Assertions.assertEquals(3, queue.peekRear());
    }

    @Test
    void testQueueIsEmptyAndIsFull() {
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertFalse(queue.isFull());

        queue.insert(1);
        queue.insert(2);
        queue.insert(3);

        Assertions.assertFalse(queue.isEmpty());
        Assertions.assertTrue(queue.isFull());

        queue.remove();
        Assertions.assertFalse(queue.isFull());
        Assertions.assertFalse(queue.isEmpty());
    }

    @Test
    void testQueueSize() {
        Assertions.assertEquals(0, queue.getSize());
        queue.insert(1);
        Assertions.assertEquals(1, queue.getSize());
        queue.insert(2);
        Assertions.assertEquals(2, queue.getSize());
        queue.insert(3);
        Assertions.assertEquals(3, queue.getSize());
        queue.remove();
        Assertions.assertEquals(2, queue.getSize());
    }

    @Test
    void testQueueToString() {
        Assertions.assertEquals("[]", queue.toString());

        queue.insert(1);
        queue.insert(2);
        Assertions.assertEquals("[1, 2]", queue.toString());

        queue.insert(3);
        Assertions.assertEquals("[1, 2, 3]", queue.toString());

        queue.remove();
        Assertions.assertEquals("[2, 3]", queue.toString());

        queue.remove();
        queue.remove();
        Assertions.assertEquals("[]", queue.toString());
    }

    @Test
    void testQueueThrowsExceptionOnEmptyPeek() {
        Assertions.assertThrows(IllegalStateException.class, queue::peekFront);
        Assertions.assertThrows(IllegalStateException.class, queue::peekRear);
    }

    @Test
    void testQueueThrowsExceptionOnRemoveFromEmptyQueue() {
        Assertions.assertThrows(IllegalStateException.class, queue::remove);
    }

    @Test
    void testQueueCapacityException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Queue<>(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Queue<>(-5));
    }

    @Test
    void testCircularBehavior() {
        // Test that queue behaves correctly after multiple insert/remove cycles
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);

        // Remove all elements
        queue.remove(); // removes 1
        queue.remove(); // removes 2
        queue.remove(); // removes 3

        // Add elements again to test circular behavior
        queue.insert(4);
        queue.insert(5);
        queue.insert(6);

        Assertions.assertEquals(4, queue.peekFront());
        Assertions.assertEquals(6, queue.peekRear());
        Assertions.assertEquals(3, queue.getSize());
        Assertions.assertTrue(queue.isFull());
    }

    @Test
    void testMixedInsertRemoveOperations() {
        // Test interleaved insert and remove operations
        queue.insert(1);
        queue.insert(2);

        Assertions.assertEquals(1, queue.remove());
        queue.insert(3);
        queue.insert(4);

        Assertions.assertEquals(2, queue.remove());
        Assertions.assertEquals(3, queue.remove());

        queue.insert(5);
        queue.insert(6);

        Assertions.assertEquals(4, queue.peekFront());
        Assertions.assertEquals(6, queue.peekRear());
        Assertions.assertEquals(3, queue.getSize());
    }

    @Test
    void testSingleElementOperations() {
        // Test operations with single element
        queue.insert(42);

        Assertions.assertEquals(42, queue.peekFront());
        Assertions.assertEquals(42, queue.peekRear());
        Assertions.assertEquals(1, queue.getSize());
        Assertions.assertFalse(queue.isEmpty());
        Assertions.assertFalse(queue.isFull());

        Assertions.assertEquals(42, queue.remove());
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertEquals(0, queue.getSize());
    }

    @Test
    void testNullValueHandling() {
        // Test queue with null values (if supported)
        Queue<String> stringQueue = new Queue<>(3);

        Assertions.assertTrue(stringQueue.insert(null));
        Assertions.assertTrue(stringQueue.insert("test"));
        Assertions.assertTrue(stringQueue.insert(null));

        Assertions.assertNull(stringQueue.peekFront());
        Assertions.assertNull(stringQueue.peekRear());
        Assertions.assertEquals(3, stringQueue.getSize());

        Assertions.assertNull(stringQueue.remove());
        Assertions.assertEquals("test", stringQueue.peekFront());
    }

    @Test
    void testStringDataType() {
        // Test queue with String data type
        Queue<String> stringQueue = new Queue<>(2);
        stringQueue.insert("first");
        stringQueue.insert("second");

        Assertions.assertEquals("first", stringQueue.peekFront());
        Assertions.assertEquals("second", stringQueue.peekRear());
    }

    @Test
    void testLargerCapacityQueue() {
        // Test queue with larger capacity
        Queue<Integer> largeQueue = new Queue<>(10);

        // Fill the queue
        for (int i = 1; i <= 10; i++) {
            Assertions.assertTrue(largeQueue.insert(i));
        }

        Assertions.assertTrue(largeQueue.isFull());
        Assertions.assertFalse(largeQueue.insert(11));

        // Remove half the elements
        for (int i = 1; i <= 5; i++) {
            Assertions.assertEquals(i, largeQueue.remove());
        }

        Assertions.assertEquals(6, largeQueue.peekFront());
        Assertions.assertEquals(10, largeQueue.peekRear());
        Assertions.assertEquals(5, largeQueue.getSize());
    }

    @Test
    void testQueueCapacityOne() {
        // Test queue with capacity of 1
        Queue<Integer> singleQueue = new Queue<>(1);

        Assertions.assertTrue(singleQueue.isEmpty());
        Assertions.assertFalse(singleQueue.isFull());

        Assertions.assertTrue(singleQueue.insert(100));
        Assertions.assertTrue(singleQueue.isFull());
        Assertions.assertFalse(singleQueue.isEmpty());
        Assertions.assertFalse(singleQueue.insert(200));

        Assertions.assertEquals(100, singleQueue.peekFront());
        Assertions.assertEquals(100, singleQueue.peekRear());

        Assertions.assertEquals(100, singleQueue.remove());
        Assertions.assertTrue(singleQueue.isEmpty());
    }

    @Test
    void testQueueWraparoundIndexing() {
        // Test that internal array indexing wraps around correctly
        queue.insert(1);
        queue.insert(2);
        queue.insert(3); // Queue full

        // Remove one element
        queue.remove(); // removes 1

        // Add another element (should wrap around)
        queue.insert(4);

        Assertions.assertEquals("[2, 3, 4]", queue.toString());
        Assertions.assertEquals(2, queue.peekFront());
        Assertions.assertEquals(4, queue.peekRear());

        // Continue the pattern
        queue.remove(); // removes 2
        queue.insert(5);

        Assertions.assertEquals(3, queue.peekFront());
        Assertions.assertEquals(5, queue.peekRear());
    }

    @Test
    void testQueueStateAfterMultipleCycles() {
        // Test queue state after multiple complete fill/empty cycles
        for (int cycle = 0; cycle < 3; cycle++) {
            // Fill the queue
            for (int i = 1; i <= 3; i++) {
                queue.insert(i + cycle * 10);
            }

            // Verify state
            Assertions.assertTrue(queue.isFull());
            Assertions.assertEquals(3, queue.getSize());

            // Empty the queue
            for (int i = 1; i <= 3; i++) {
                queue.remove();
            }

            // Verify empty state
            Assertions.assertTrue(queue.isEmpty());
            Assertions.assertEquals(0, queue.getSize());
        }
    }

    @Test
    void testQueueConsistencyAfterOperations() {
        // Test that queue maintains consistency after various operations
        queue.insert(10);
        queue.insert(20);

        int firstRemoved = queue.remove();
        queue.insert(30);
        queue.insert(40);

        int secondRemoved = queue.remove();
        queue.insert(50);

        // Verify the order is maintained
        Assertions.assertEquals(10, firstRemoved);
        Assertions.assertEquals(20, secondRemoved);
        Assertions.assertEquals(30, queue.peekFront());
        Assertions.assertEquals(50, queue.peekRear());
    }
}
