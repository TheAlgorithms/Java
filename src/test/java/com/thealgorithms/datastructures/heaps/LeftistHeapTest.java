package com.thealgorithms.datastructures.heaps;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeftistHeapTest {

    @Test
    void testLeftistHeap() {
        LeftistHeap heap = new LeftistHeap();
        Assertions.assertTrue(heap.isEmpty());
        heap.insert(6);
        Assertions.assertTrue(!heap.isEmpty());
        heap.insert(2);
        heap.insert(3);
        heap.insert(1);
        heap.in_order();
        Assertions.assertTrue(heap.in_order().toString().equals("[6, 2, 3, 1]"));
        Assertions.assertTrue(heap.extract_min() == 1);
        Assertions.assertTrue(heap.in_order().toString().equals("[6, 2, 3]"));
        heap.insert(8);
        heap.insert(12);
        heap.insert(4);
        Assertions.assertTrue(heap.in_order().toString().equals("[8, 3, 12, 2, 6, 4]"));
        heap.clear();
        Assertions.assertTrue(heap.isEmpty());
    }
}