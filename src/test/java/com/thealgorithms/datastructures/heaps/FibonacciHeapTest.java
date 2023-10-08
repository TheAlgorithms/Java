package com.thealgorithms.datastructures.heaps;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FibonacciHeapTest {

    @Test
    void testHeap() {
        FibonacciHeap fibonacciHeap = new FibonacciHeap();
        fibonacciHeap.insert(5);
        fibonacciHeap.insert(3);
        fibonacciHeap.insert(1);
        fibonacciHeap.insert(18);
        fibonacciHeap.insert(33);

        Assertions.assertEquals(fibonacciHeap.findMin().getKey(), 1);
        fibonacciHeap.deleteMin();
        Assertions.assertEquals(fibonacciHeap.findMin().getKey(), 3);
    }
}
