/******************************************************************
 *                  Tests For Binomial Heap                       *
 *                                                                *
 * For deatiled info regarding Binomial-Heap refer to below link  *
 *      wiki -  https://en.wikipedia.org/wiki/Binomial_heap       *
 *                                                                *
 *                                                                *
 *               Author - Prabhat-Kumar-42                        *
 *        Github - https://github.com/Prabhat-Kumar-42            *
 *                                                                *
 *****************************************************************/

package com.thealgorithms.datastructures.heaps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BinomialHeapTest {

    @Test
    public void testInsertAndFindMin() {
        // Create a new binomial heap
        BinomialHeap binomialHeap = new BinomialHeap();

        // Insert some elements into the heap
        binomialHeap.insert(10);
        binomialHeap.insert(20);
        binomialHeap.insert(5);
        binomialHeap.insert(30);
        binomialHeap.insert(25);

        // Test if the minimum element is correctly found
        assertEquals(5, binomialHeap.findMin());
    }

    @Test
    public void testExtractMin() {
        // Create a new binomial heap
        BinomialHeap binomialHeap = new BinomialHeap();

        // Insert elements into the heap
        binomialHeap.insert(10);
        binomialHeap.insert(20);
        binomialHeap.insert(5);
        binomialHeap.insert(30);
        binomialHeap.insert(25);

        // Extract the minimum elements and test if they are correct
        assertEquals(5, binomialHeap.extractMin());
        assertEquals(10, binomialHeap.extractMin());
        assertEquals(20, binomialHeap.extractMin());
    }

    @Test
    public void testDecreaseKey() {
        // Create a new binomial heap
        BinomialHeap binomialHeap = new BinomialHeap();

        // Insert elements into the heap
        binomialHeap.insert(10);
        binomialHeap.insert(20);
        binomialHeap.insert(5);
        binomialHeap.insert(30);
        binomialHeap.insert(25);

        // Decrease the key of an element and test if the minimum element is updated correctly
        binomialHeap.decreaseKey(30, 1);
        assertEquals(1, binomialHeap.findMin());
    }

    @Test
    public void testDelete() {
        // Create a new binomial heap
        BinomialHeap binomialHeap = new BinomialHeap();

        // Insert elements into the heap
        binomialHeap.insert(10);
        binomialHeap.insert(20);
        binomialHeap.insert(5);
        binomialHeap.insert(30);
        binomialHeap.insert(25);

        // Delete an element from the heap and test if the minimum element is updated correctly
        binomialHeap.delete(20);
        assertEquals(5, binomialHeap.findMin());
    }

    @Test
    public void testEmptyHeap() {
        // Create an empty binomial heap
        BinomialHeap binomialHeap = new BinomialHeap();

        // Test if findMin and extractMin return the correct value for an empty heap
        assertEquals(Integer.MIN_VALUE, binomialHeap.findMin());
        assertEquals(Integer.MIN_VALUE, binomialHeap.extractMin());
    }
}
