package com.thealgorithms.matrix;

import java.util.Objects;

/**
 * General-purpose search utilities for 2D matrices.
 *
 * <p>This class focuses on <b>membership queries</b> ("does the matrix contain the value?") for
 * arbitrary 2D matrices. Unlike algorithms that rely on sorted rows/columns, these methods make no
 * ordering assumptions.
 *
 * <p>Reference: Linear search
 * https://en.wikipedia.org/wiki/Linear_search
 *
 * <p><b>Complexity</b>
 *
 * <ul>
 *   <li>Time: {@code O(m * n)} in the worst case (scan all elements).</li>
 *   <li>Space: {@code O(1)}.</li>
 * </ul>
 */
public final class SearchMatrix {

    private SearchMatrix() {
    }

    /**
     * Searches for {@code target} in any 2D object matrix.
     *
     * <p>This method makes <b>no ordering assumptions</b> and performs a linear scan.
     *
     * <p>This method is null-safe:
     *
     * <ul>
     *   <li>If {@code matrix} is {@code null} or has zero rows, returns {@code false}.</li>
     *   <li>Null rows are treated as empty rows.</li>
     *   <li>Elements are compared using {@link Objects#equals(Object, Object)} (so {@code null}
     *       targets are supported).</li>
     * </ul>
     *
     * @param matrix the input matrix (may be jagged)
     * @param target the element to find (may be {@code null})
     * @param <T> element type
     * @return {@code true} if the target exists in the matrix, {@code false} otherwise
     */
    public static <T> boolean contains(final T[][] matrix, final T target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        for (final T[] row : matrix) {
            if (row == null || row.length == 0) {
                continue;
            }
            for (final T value : row) {
                if (Objects.equals(value, target)) {
                    return true;
                }
            }
        }

        return false;
    }
}
