package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Comprehensive test suite for FenwickTree (Binary Indexed Tree).
 * Tests various operations, edge cases, and error conditions.
 * 
 * @author TheAlgorithms Contributors
 */
class FenwickTreeTest {

    @Test
    void testConstructorWithSize() {
        FenwickTree tree = new FenwickTree(5);
        assertEquals(5, tree.size(), "Tree size should match constructor parameter");
        assertEquals(0, tree.totalSum(), "Initial total sum should be 0");
    }

    @Test
    void testConstructorWithInvalidSize() {
        assertThrows(IllegalArgumentException.class, () -> new FenwickTree(0), 
                     "Constructor should throw exception for size 0");
        assertThrows(IllegalArgumentException.class, () -> new FenwickTree(-1), 
                     "Constructor should throw exception for negative size");
    }

    @Test
    void testConstructorWithArray() {
        int[] array = {1, 3, 5, 7, 9, 11};
        FenwickTree tree = new FenwickTree(array);
        
        assertEquals(array.length, tree.size(), "Tree size should match array length");
        assertEquals(36, tree.totalSum(), "Total sum should equal sum of array elements");
        
        // Verify individual elements
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], tree.get(i), "Element at index " + i + " should match original array");
        }
    }

    @Test
    void testConstructorWithNullArray() {
        assertThrows(IllegalArgumentException.class, () -> new FenwickTree((int[]) null), 
                     "Constructor should throw exception for null array");
    }

    @Test
    void testConstructorWithEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> new FenwickTree(new int[0]), 
                     "Constructor should throw exception for empty array");
    }

    @Test
    void testBasicUpdateAndQuery() {
        FenwickTree tree = new FenwickTree(5);
        
        tree.update(0, 1);
        tree.update(1, 3);
        tree.update(2, 5);
        tree.update(3, 7);
        tree.update(4, 9);
        
        assertEquals(1, tree.query(0), "Query(0) should return 1");
        assertEquals(4, tree.query(1), "Query(1) should return 1+3=4");
        assertEquals(9, tree.query(2), "Query(2) should return 1+3+5=9");
        assertEquals(16, tree.query(3), "Query(3) should return 1+3+5+7=16");
        assertEquals(25, tree.query(4), "Query(4) should return 1+3+5+7+9=25");
    }

    @Test
    void testRangeQuery() {
        int[] array = {1, 3, 5, 7, 9, 11};
        FenwickTree tree = new FenwickTree(array);
        
        assertEquals(1, tree.rangeQuery(0, 0), "Range [0,0] should return 1");
        assertEquals(4, tree.rangeQuery(0, 1), "Range [0,1] should return 1+3=4");
        assertEquals(9, tree.rangeQuery(0, 2), "Range [0,2] should return 1+3+5=9");
        assertEquals(3, tree.rangeQuery(1, 1), "Range [1,1] should return 3");
        assertEquals(8, tree.rangeQuery(1, 2), "Range [1,2] should return 3+5=8");
        assertEquals(15, tree.rangeQuery(1, 3), "Range [1,3] should return 3+5+7=15");
        assertEquals(27, tree.rangeQuery(2, 5), "Range [2,5] should return 5+7+9+11=32");
    }

    @Test
    void testGetAndSet() {
        FenwickTree tree = new FenwickTree(5);
        
        // Set initial values
        tree.set(0, 10);
        tree.set(1, 20);
        tree.set(2, 30);
        tree.set(3, 40);
        tree.set(4, 50);
        
        assertEquals(10, tree.get(0), "Get(0) should return 10");
        assertEquals(20, tree.get(1), "Get(1) should return 20");
        assertEquals(30, tree.get(2), "Get(2) should return 30");
        assertEquals(40, tree.get(3), "Get(3) should return 40");
        assertEquals(50, tree.get(4), "Get(4) should return 50");
        
        // Update values
        tree.set(2, 100);
        assertEquals(100, tree.get(2), "Get(2) should return 100 after update");
        assertEquals(220, tree.totalSum(), "Total sum should be updated correctly");
    }

    @Test
    void testMultipleUpdates() {
        FenwickTree tree = new FenwickTree(3);
        
        tree.update(1, 5);
        tree.update(1, 3);
        tree.update(1, -2);
        
        assertEquals(6, tree.get(1), "Multiple updates should be cumulative: 5+3-2=6");
        assertEquals(6, tree.query(1), "Query should reflect cumulative updates");
    }

    @Test
    void testNegativeValues() {
        FenwickTree tree = new FenwickTree(4);
        
        tree.update(0, -5);
        tree.update(1, 10);
        tree.update(2, -3);
        tree.update(3, 8);
        
        assertEquals(-5, tree.get(0), "Negative values should be handled correctly");
        assertEquals(5, tree.query(1), "Query with negative values: -5+10=5");
        assertEquals(2, tree.query(2), "Query with negative values: -5+10-3=2");
        assertEquals(10, tree.totalSum(), "Total sum with negative values: -5+10-3+8=10");
    }

    @Test
    void testBoundaryAccess() {
        FenwickTree tree = new FenwickTree(3);
        tree.update(0, 1);
        tree.update(1, 2);
        tree.update(2, 3);
        
        // Test valid boundary access
        assertEquals(1, tree.query(0), "First index query should work");
        assertEquals(6, tree.query(2), "Last index query should work");
        assertEquals(1, tree.get(0), "First index get should work");
        assertEquals(3, tree.get(2), "Last index get should work");
    }

    @Test
    void testOutOfBoundsAccess() {
        FenwickTree tree = new FenwickTree(3);
        
        // Test out of bounds for update
        assertThrows(IndexOutOfBoundsException.class, () -> tree.update(-1, 5), 
                     "Update with negative index should throw exception");
        assertThrows(IndexOutOfBoundsException.class, () -> tree.update(3, 5), 
                     "Update with index >= size should throw exception");
        
        // Test out of bounds for query
        assertThrows(IndexOutOfBoundsException.class, () -> tree.query(-1), 
                     "Query with negative index should throw exception");
        assertThrows(IndexOutOfBoundsException.class, () -> tree.query(3), 
                     "Query with index >= size should throw exception");
        
        // Test out of bounds for get
        assertThrows(IndexOutOfBoundsException.class, () -> tree.get(-1), 
                     "Get with negative index should throw exception");
        assertThrows(IndexOutOfBoundsException.class, () -> tree.get(3), 
                     "Get with index >= size should throw exception");
        
        // Test out of bounds for set
        assertThrows(IndexOutOfBoundsException.class, () -> tree.set(-1, 5), 
                     "Set with negative index should throw exception");
        assertThrows(IndexOutOfBoundsException.class, () -> tree.set(3, 5), 
                     "Set with index >= size should throw exception");
    }

    @Test
    void testInvalidRangeQuery() {
        FenwickTree tree = new FenwickTree(5);
        
        assertThrows(IndexOutOfBoundsException.class, () -> tree.rangeQuery(-1, 2), 
                     "Range query with negative left should throw exception");
        assertThrows(IndexOutOfBoundsException.class, () -> tree.rangeQuery(0, 5), 
                     "Range query with right >= size should throw exception");
        assertThrows(IndexOutOfBoundsException.class, () -> tree.rangeQuery(3, 2), 
                     "Range query with left > right should throw exception");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 50, 100})
    void testTreeWithDifferentSizes(int size) {
        FenwickTree tree = new FenwickTree(size);
        
        // Fill with sequential values
        for (int i = 0; i < size; i++) {
            tree.update(i, i + 1);
        }
        
        // Verify total sum using arithmetic series formula: n*(n+1)/2
        int expectedSum = size * (size + 1) / 2;
        assertEquals(expectedSum, tree.totalSum(), "Total sum should match arithmetic series");
        
        // Verify prefix sums
        for (int i = 0; i < size; i++) {
            int expectedPrefixSum = (i + 1) * (i + 2) / 2;
            assertEquals(expectedPrefixSum, tree.query(i), 
                        "Prefix sum at index " + i + " should be correct");
        }
    }

    @Test
    void testEmptyTreeOperations() {
        FenwickTree tree = new FenwickTree(1);
        
        assertEquals(0, tree.totalSum(), "Empty tree should have total sum 0");
        assertEquals(0, tree.get(0), "Empty tree element should be 0");
        assertEquals(0, tree.query(0), "Empty tree query should return 0");
    }

    @Test
    void testLargeValues() {
        FenwickTree tree = new FenwickTree(3);
        
        int largeValue = Integer.MAX_VALUE / 3;
        tree.update(0, largeValue);
        tree.update(1, largeValue);
        tree.update(2, largeValue);
        
        assertEquals(largeValue, tree.get(0), "Large values should be handled correctly");
        assertEquals(largeValue * 2, tree.query(1), "Large value queries should work");
        assertEquals(largeValue * 3, tree.totalSum(), "Large value total sum should work");
    }

    @Test
    void testConsistencyWithNaiveImplementation() {
        int[] array = {5, 3, 8, 1, 9, 2, 7, 4, 6};
        FenwickTree tree = new FenwickTree(array);
        
        // Test all possible range queries and compare with naive implementation
        for (int left = 0; left < array.length; left++) {
            for (int right = left; right < array.length; right++) {
                int expected = 0;
                for (int k = left; k <= right; k++) {
                    expected += array[k];
                }
                assertEquals(expected, tree.rangeQuery(left, right), 
                           "Range query [" + left + "," + right + "] should match naive sum");
            }
        }
    }
}