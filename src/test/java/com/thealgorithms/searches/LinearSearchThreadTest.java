package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LinearSearchThreadTest {

    /**
     * Test for finding an element that is present in the array.
     */
    @Test
    void testSearcherFound() throws InterruptedException {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Searcher searcher = new Searcher(array, 0, array.length, 5);
        searcher.start();
        searcher.join();
        assertTrue(searcher.getResult(), "The element 5 should be found in the array.");
    }

    /**
     * Test for finding an element that is not present in the array.
     */
    @Test
    void testSearcherNotFound() throws InterruptedException {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Searcher searcher = new Searcher(array, 0, array.length, 11);
        searcher.start();
        searcher.join();
        assertFalse(searcher.getResult(), "The element 11 should not be found in the array.");
    }

    /**
     * Test for searching a segment of the array.
     */
    @Test
    void testSearcherSegmentFound() throws InterruptedException {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Searcher searcher = new Searcher(array, 0, 5, 3);
        searcher.start();
        searcher.join();
        assertTrue(searcher.getResult(), "The element 3 should be found in the segment.");
    }

    /**
     * Test for searching an empty array segment.
     */
    @Test
    void testSearcherEmptySegment() throws InterruptedException {
        int[] array = {};
        Searcher searcher = new Searcher(array, 0, 0, 1); // Empty array
        searcher.start();
        searcher.join();
        assertFalse(searcher.getResult(), "The element should not be found in an empty segment.");
    }

    /**
     * Test for searching with random numbers.
     */
    @Test
    void testSearcherRandomNumbers() throws InterruptedException {
        int size = 200;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        int target = array[(int) (Math.random() * size)]; // Randomly select a target that is present
        Searcher searcher = new Searcher(array, 0, size, target);
        searcher.start();
        searcher.join();
        assertTrue(searcher.getResult(), "The randomly selected target should be found in the array.");
    }
}
