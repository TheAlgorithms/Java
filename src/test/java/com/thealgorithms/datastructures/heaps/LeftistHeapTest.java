package com.thealgorithms.datastructures.heaps;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeftistHeapTest {

    @Test
    void testIsEmpty() {
        LeftistHeap heap = new LeftistHeap();
        Assertions.assertTrue(heap.isEmpty(), "Heap should be empty initially.");

        heap.insert(10);
        Assertions.assertFalse(heap.isEmpty(), "Heap should not be empty after insertion.");

        heap.clear();
        Assertions.assertTrue(heap.isEmpty(), "Heap should be empty after clearing.");
    }

    @Test
    void testInsertAndExtractMin() {
        LeftistHeap heap = new LeftistHeap();
        heap.insert(6);
        heap.insert(2);
        heap.insert(3);
        heap.insert(1);

        Assertions.assertEquals(1, heap.extractMin(), "Minimum should be 1.");
        Assertions.assertEquals(2, heap.extractMin(), "Next minimum should be 2.");
        Assertions.assertEquals(3, heap.extractMin(), "Next minimum should be 3.");
        Assertions.assertEquals(6, heap.extractMin(), "Next minimum should be 6.");
        Assertions.assertEquals(-1, heap.extractMin(), "Extracting from an empty heap should return -1.");
    }

    @Test
    void testMerge() {
        LeftistHeap heap1 = new LeftistHeap();
        heap1.insert(1);
        heap1.insert(3);
        heap1.insert(5);

        LeftistHeap heap2 = new LeftistHeap();
        heap2.insert(2);
        heap2.insert(4);
        heap2.insert(6);

        heap1.merge(heap2);

        Assertions.assertEquals(1, heap1.extractMin(), "After merging, minimum should be 1.");
        Assertions.assertEquals(2, heap1.extractMin(), "Next minimum should be 2.");
        Assertions.assertEquals(3, heap1.extractMin(), "Next minimum should be 3.");
        Assertions.assertEquals(4, heap1.extractMin(), "Next minimum should be 4.");
        Assertions.assertEquals(5, heap1.extractMin(), "Next minimum should be 5.");
        Assertions.assertEquals(6, heap1.extractMin(), "Next minimum should be 6.");
        Assertions.assertEquals(-1, heap1.extractMin(), "Extracting from an empty heap should return -1.");
    }

    @Test
    void testInOrderTraversal() {
        LeftistHeap heap = new LeftistHeap();
        heap.insert(10);
        heap.insert(5);
        heap.insert(20);
        heap.insert(15);
        heap.insert(30);

        Assertions.assertEquals("[20, 15, 30, 5, 10]", heap.inOrder().toString(), "In-order traversal should match the expected output.");
    }

    @Test
    void testMultipleExtractions() {
        LeftistHeap heap = new LeftistHeap();
        heap.insert(10);
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);

        // Extract multiple elements
        Assertions.assertEquals(3, heap.extractMin());
        Assertions.assertEquals(5, heap.extractMin());
        Assertions.assertEquals(8, heap.extractMin());
        Assertions.assertEquals(10, heap.extractMin());
        Assertions.assertEquals(-1, heap.extractMin(), "Extracting from an empty heap should return -1.");
    }
}
