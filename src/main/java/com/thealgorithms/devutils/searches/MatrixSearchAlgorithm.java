package com.thealgorithms.devutils.searches;

/**
 * The common interface of most searching algorithms that search in matrixes.
 *
 * @author Aitor Fidalgo (https://github.com/aitorfi)
 */
public interface MatrixSearchAlgorithm {
    /**
     * @param key is an element which should be found
     * @param matrix is a matrix where the element should be found
     * @param <T> Comparable type
     * @return array containing the first found coordinates of the element
     */
    <T extends Comparable<T>> int[] find(T[][] matrix, T key);
}
