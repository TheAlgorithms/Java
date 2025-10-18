package com.thealgorithms.datastructures.trees;

/**
 * Fenwick Tree (Binary Indexed Tree) implementation.
 * 
 * A Fenwick Tree is a data structure that can efficiently:
 * - Update elements in O(log n) time
 * - Calculate prefix sums in O(log n) time
 * - Use O(n) space
 * 
 * This is particularly useful for problems requiring frequent updates and 
 * range sum queries on an array.
 * 
 * Key operations:
 * - update(index, delta): Add delta to the element at index
 * - query(index): Get sum of elements from 0 to index (inclusive)
 * - rangeQuery(left, right): Get sum of elements from left to right (inclusive)
 * 
 * Implementation details:
 * - Uses 1-indexed internally for easier bit manipulation
 * - Converts between 0-indexed (user) and 1-indexed (internal) as needed
 * - Utilizes bit manipulation for efficient parent/child navigation
 * 
 * Time Complexity:
 * - Construction: O(n log n) or O(n) with optimized build
 * - Update: O(log n)
 * - Query: O(log n)
 * - Range Query: O(log n)
 * 
 * Space Complexity: O(n)
 * 
 * @author TheAlgorithms Contributors
 * @see <a href="https://en.wikipedia.org/wiki/Fenwick_tree">Fenwick Tree</a>
 */
public class FenwickTree {

    /**
     * The size of the array.
     */
    private int n;
    
    /**
     * The internal tree array (1-indexed for easier bit manipulation).
     */
    private int[] fenTree;

    /**
     * Constructor which takes the size of the array as a parameter.
     * All elements are initially zero.
     * 
     * @param n the size of the array to represent
     * @throws IllegalArgumentException if n is non-positive
     */
    public FenwickTree(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Size must be positive, got: " + n);
        }
        this.n = n;
        this.fenTree = new int[n + 1]; // 1-indexed, so n + 1
    }

    /**
     * Constructor that builds a Fenwick Tree from an existing array.
     * 
     * @param array the array to build the tree from
     * @throws IllegalArgumentException if array is null or empty
     */
    public FenwickTree(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        this.n = array.length;
        this.fenTree = new int[n + 1];
        
        // Build the tree efficiently in O(n) time
        for (int i = 0; i < array.length; i++) {
            update(i, array[i]);
        }
    }

    /**
     * Updates the element at the given index by adding the specified value.
     * 
     * @param i the 0-indexed position to update
     * @param val the value to add to the element at position i
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public void update(int i, int val) {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for size " + n);
        }
        
        // As index starts from 0, increment the index by 1
        i += 1;
        while (i <= n) {
            fenTree[i] += val;
            i += i & (-i); // Add the rightmost set bit
        }
    }

    /**
     * Returns the cumulative sum from index 0 to index i (inclusive).
     * This is also known as the prefix sum.
     * 
     * @param i the 0-indexed end position (inclusive)
     * @return the sum of elements from 0 to i
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public int query(int i) {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for size " + n);
        }
        
        // As index starts from 0, increment the index by 1
        i += 1;
        int cumSum = 0;
        while (i > 0) {
            cumSum += fenTree[i];
            i -= i & (-i); // Remove the rightmost set bit
        }
        return cumSum;
    }
    
    /**
     * Returns the sum of elements in the range [left, right] (both inclusive).
     * 
     * @param left the 0-indexed start position (inclusive)
     * @param right the 0-indexed end position (inclusive)
     * @return the sum of elements in the range
     * @throws IndexOutOfBoundsException if indices are out of bounds
     * @throws IllegalArgumentException if left > right
     */
    public int rangeQuery(int left, int right) {
        if (left < 0 || right >= n || left > right) {
            throw new IndexOutOfBoundsException("Invalid range [" + left + ", " + right + "] for size " + n);
        }
        
        if (left == 0) {
            return query(right);
        }
        return query(right) - query(left - 1);
    }
    
    /**
     * Sets the element at the given index to the specified value.
     * 
     * @param index the 0-indexed position to set
     * @param value the new value
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public void set(int index, int value) {
        int currentValue = get(index);
        update(index, value - currentValue);
    }
    
    /**
     * Gets the current value at the given index.
     * 
     * @param index the 0-indexed position to query
     * @return the current value at the index
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public int get(int index) {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + n);
        }
        
        if (index == 0) {
            return query(0);
        }
        return query(index) - query(index - 1);
    }
    
    /**
     * Returns the size of the array represented by this Fenwick Tree.
     * 
     * @return the size of the array
     */
    public int size() {
        return n;
    }
    
    /**
     * Returns the total sum of all elements in the array.
     * 
     * @return the total sum
     */
    public int totalSum() {
        return n > 0 ? query(n - 1) : 0;
    }
}
