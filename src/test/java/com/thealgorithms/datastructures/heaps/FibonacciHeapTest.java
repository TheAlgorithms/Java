package com.thealgorithms.datastructures.heaps;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FibonacciHeapTest {

    @Test
    void testHeapInsertionAndMinimum() {
        FibonacciHeap fibonacciHeap = new FibonacciHeap();
        fibonacciHeap.insert(5);
        fibonacciHeap.insert(3);
        fibonacciHeap.insert(1);
        fibonacciHeap.insert(18);
        fibonacciHeap.insert(33);

        Assertions.assertEquals(1, fibonacciHeap.findMin().getKey());
        fibonacciHeap.deleteMin();
        Assertions.assertEquals(3, fibonacciHeap.findMin().getKey());
    }

    @Test
    void testDeleteMinOnSingleElementHeap() {
        FibonacciHeap fibonacciHeap = new FibonacciHeap(10);
        Assertions.assertEquals(10, fibonacciHeap.findMin().getKey());
        fibonacciHeap.deleteMin();
        Assertions.assertTrue(fibonacciHeap.empty());
    }

    @Test
    void testHeapMeld() {
        FibonacciHeap heap1 = new FibonacciHeap();
        FibonacciHeap heap2 = new FibonacciHeap();
        heap1.insert(1);
        heap1.insert(2);
        heap2.insert(3);
        heap2.insert(4);

        heap1.meld(heap2);
        Assertions.assertEquals(1, heap1.findMin().getKey());
    }

    @Test
    void testHeapSize() {
        FibonacciHeap fibonacciHeap = new FibonacciHeap();
        Assertions.assertEquals(0, fibonacciHeap.size());
        fibonacciHeap.insert(5);
        Assertions.assertEquals(1, fibonacciHeap.size());
        fibonacciHeap.insert(3);
        Assertions.assertEquals(2, fibonacciHeap.size());
        fibonacciHeap.deleteMin();
        Assertions.assertEquals(1, fibonacciHeap.size());
    }

    @Test
    void testCountersRep() {
        FibonacciHeap fibonacciHeap = new FibonacciHeap();
        fibonacciHeap.insert(5);
        fibonacciHeap.insert(3);
        fibonacciHeap.insert(8);
        fibonacciHeap.insert(1);

        int[] counters = fibonacciHeap.countersRep();
        Assertions.assertEquals(4, counters[0]);
        Assertions.assertEquals(0, counters[1]);
    }

    @Test
    void testDeleteMinMultipleElements() {
        FibonacciHeap fibonacciHeap = new FibonacciHeap();
        fibonacciHeap.insert(5);
        fibonacciHeap.insert(2);
        fibonacciHeap.insert(8);
        fibonacciHeap.insert(1);

        Assertions.assertEquals(1, fibonacciHeap.findMin().getKey());
        fibonacciHeap.deleteMin();
        Assertions.assertEquals(2, fibonacciHeap.findMin().getKey());
    }

    @Test
    void testInsertNegativeKeys() {
        FibonacciHeap fibonacciHeap = new FibonacciHeap();
        fibonacciHeap.insert(-10);
        fibonacciHeap.insert(-5);
        fibonacciHeap.insert(-20);

        Assertions.assertEquals(-20, fibonacciHeap.findMin().getKey());
    }

    @Test
    void testDeleteOnEmptyHeap() {
        FibonacciHeap fibonacciHeap = new FibonacciHeap();
        Assertions.assertThrows(NullPointerException.class, () -> { fibonacciHeap.delete(fibonacciHeap.findMin()); });
    }

    @Test
    void testPotentialCalculation() {
        FibonacciHeap fibonacciHeap = new FibonacciHeap();
        fibonacciHeap.insert(10);
        fibonacciHeap.insert(20);

        Assertions.assertEquals(2, fibonacciHeap.potential()); // 2 trees, no marked nodes
        var node = fibonacciHeap.findMin();
        fibonacciHeap.delete(node);
        Assertions.assertEquals(1, fibonacciHeap.potential());
    }
}
