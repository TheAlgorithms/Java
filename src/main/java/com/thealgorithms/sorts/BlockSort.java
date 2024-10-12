package com.thealgorithms.sorts;

import java.util.Arrays;

/**
 * Implementation of Block Sort algorithm that implements the SortAlgorithm interface.
 * 
 * Block Sort is a distribution sorting algorithm that divides the array into blocks,
 * sorts each block, and merges the blocks to produce a sorted array.
 * 
 * The method works as follows:
 * <ol>
 *     <li>Divide the input array into blocks.</li>
 *     <li>Sort each block individually.</li>
 *     <li>Merge the sorted blocks to produce a fully sorted array.</li>
 * </ol>
 */
public class BlockSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        return blockSort(array);
    }

    /**
     * Sorts an array using the Block Sort algorithm.
     * 
     * @param arr the array to be sorted.
     * @param <T> the type of elements to be sorted, must be comparable.
     * @return the sorted array.
     */
    private <T extends Comparable<? super T>> T[] blockSort(T[] arr) {
        if (arr.length <= 1) {
            return arr; // Already sorted
        }

        int blockSize = (int) Math.sqrt(arr.length); // Block size is the square root of the array size
        int numBlocks = (arr.length + blockSize - 1) / blockSize; // Calculate number of blocks

        // Create an array of blocks
        T[][] blocks = (T[][]) new Comparable[numBlocks][];
        
        // Populate the blocks
        for (int i = 0; i < numBlocks; i++) {
            int start = i * blockSize;
            int end = Math.min(start + blockSize, arr.length);
            blocks[i] = Arrays.copyOfRange(arr, start, end);
            Arrays.sort(blocks[i]); // Sort each block
        }

        // Merge the sorted blocks
        return mergeBlocks(blocks);
    }

    /**
     * Merges sorted blocks into a single sorted array.
     * 
     * @param blocks the array of sorted blocks.
     * @param <T> the type of elements in the blocks, must be comparable.
     * @return the merged sorted array.
     */
    private <T extends Comparable<? super T>> T[] mergeBlocks(T[][] blocks) {
        int totalLength = Arrays.stream(blocks).mapToInt(b -> b.length).sum();
        T[] result = (T[]) new Comparable[totalLength];
        int index = 0;

        for (T[] block : blocks) {
            for (T element : block) {
                result[index++] = element; // Add each element from the block to the result array
            }
        }

        return result;
    }
}
