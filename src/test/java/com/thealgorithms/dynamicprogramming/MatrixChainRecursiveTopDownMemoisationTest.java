package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MatrixChainRecursiveTopDownMemoisationTest {

    /**
     * Test case for four matrices with dimensions 1x2, 2x3, 3x4, and 4x5.
     * The expected minimum number of multiplications is 38.
     */
    @Test
    void testFourMatrices() {
        int[] dimensions = {1, 2, 3, 4, 5};
        int expected = 38;
        int actual = MatrixChainRecursiveTopDownMemoisation.memoizedMatrixChain(dimensions);
        assertEquals(expected, actual, "The minimum number of multiplications should be 38.");
    }

    /**
     * Test case for three matrices with dimensions 10x20, 20x30, and 30x40.
     * The expected minimum number of multiplications is 6000.
     */
    @Test
    void testThreeMatrices() {
        int[] dimensions = {10, 20, 30, 40};
        int expected = 18000;
        int actual = MatrixChainRecursiveTopDownMemoisation.memoizedMatrixChain(dimensions);
        assertEquals(expected, actual, "The minimum number of multiplications should be 18000.");
    }

    /**
     * Test case for two matrices with dimensions 5x10 and 10x20.
     * The expected minimum number of multiplications is 1000.
     */
    @Test
    void testTwoMatrices() {
        int[] dimensions = {5, 10, 20};
        int expected = 1000;
        int actual = MatrixChainRecursiveTopDownMemoisation.memoizedMatrixChain(dimensions);
        assertEquals(expected, actual, "The minimum number of multiplications should be 1000.");
    }

    /**
     * Test case for a single matrix.
     * The expected minimum number of multiplications is 0, as there are no multiplications needed.
     */
    @Test
    void testSingleMatrix() {
        int[] dimensions = {10, 20}; // Single matrix dimensions
        int expected = 0;
        int actual = MatrixChainRecursiveTopDownMemoisation.memoizedMatrixChain(dimensions);
        assertEquals(expected, actual, "The minimum number of multiplications should be 0.");
    }

    /**
     * Test case for matrices with varying dimensions.
     * The expected minimum number of multiplications is calculated based on the dimensions provided.
     */
    @Test
    void testVaryingDimensions() {
        int[] dimensions = {2, 3, 4, 5, 6}; // Dimensions for 4 matrices
        int expected = 124; // Expected value needs to be calculated based on the problem
        int actual = MatrixChainRecursiveTopDownMemoisation.memoizedMatrixChain(dimensions);
        assertEquals(expected, actual, "The minimum number of multiplications should be 124.");
    }
}
