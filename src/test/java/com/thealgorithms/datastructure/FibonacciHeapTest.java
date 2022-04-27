package com.thealgorithms.datastructures;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FibonacciHeapTest {

    @Test
    void testHeap()
    {
        FibonacciHeap fibonacciHeap = new FibonacciHeap();
        fibonacciHeap.insert(5);
        fibonacciHeap.insert(3);
        fibonacciHeap.insert(1);
        fibonacciHeap.insert(18);
        fibonacciHeap.insert(33);


        assertEquals(fibonacciHeap.findMin().getKey(), 1);
        fibonacciHeap.deleteMin();
        assertEquals(fibonacciHeap.findMin().getKey(), 3);
    }
}
