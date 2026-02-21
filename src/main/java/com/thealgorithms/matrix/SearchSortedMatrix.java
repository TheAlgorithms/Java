package com.thealgorithms.matrix;

import java.util.Comparator;
import java.util.Objects;

/**
 * Provides an efficient search operation for a 2D matrix that is sorted in both directions.
 *
 * <p><b>Assumptions</b>
 *
 * <ul>
 *   <li>Each row is sorted in ascending order (left &#8594; right).</li>
 *   <li>Each column is sorted in ascending order (top &#8595; bottom).</li>
 *   <li>The input is a rectangular matrix (not jagged) with non-null rows.</li>
 * </ul>
 *
 * <p><b>Algorithm idea (search space reduction)</b>
 *
 * <p>Start in the top-right corner. At any position {@code (row, col)}:
 *
 * <ul>
 *   <li>If {@code matrix[row][col] > target}, then all values below in the same column are
 *       {@code >= matrix[row][col]} and therefore also {@code > target}; move left.</li>
 *   <li>If {@code matrix[row][col] < target}, then all values left in the same row are
 *       {@code <= matrix[row][col]} and therefore also {@code < target}; move down.</li>
 * </ul>
 *
 * <p>Each move removes an entire row or column from consideration, so the search performs at most
 * {@code rows + cols - 1} comparisons.
 *
 * <p>Reference: Saddleback search ("staircase" search)
 * https://en.wikipedia.org/wiki/Saddleback_search
 *
 * <p><b>Alternatives</b>
 *
 * <ul>
 *   <li>Binary search in each row: {@code O(m * log(n))}.</li>
 *   <li>Binary search in each column: {@code O(n * log(m))}.</li>
 * </ul>
 *
 * <p><b>Complexity</b>
 *
 * <ul>
 *   <li>Time: {@code O(m + n)} where {@code m} is the number of rows and {@code n} is the number of columns.</li>
 *   <li>Space: {@code O(1)}.</li>
 * </ul>
 */
public final class SearchSortedMatrix {

    private SearchSortedMatrix() {
    }

    /**
     * Searches a matrix that is sorted ascending by row and by column.
     *
     * <p>This overload is intended for object matrices and uses the provided {@code comparator}.
     * The matrix must be <b>rectangular</b> (not jagged) with non-null rows.
     *
     * <p>Note: If the matrix contains {@code null} elements (or {@code target} is {@code null}),
     * the {@code comparator} must define how to order {@code null} values (for example,
     * {@link Comparator#nullsFirst(Comparator)}).
     *
     * @param matrix the input rectangular matrix
     * @param target the value to locate
     * @param comparator comparator consistent with the matrix sort order
     * @param <T> element type
     * @return whether the target exists in the matrix
     * @throws IllegalArgumentException if the matrix is jagged or contains null rows
     * @throws NullPointerException if {@code comparator} is null
     */
    public static <T> boolean search(final T[][] matrix, final T target, final Comparator<? super T> comparator) {
        if (matrix == null) {
            return false;
        }
        if (matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null) {
            return false;
        }
        if (matrix[0].length == 0) {
            return false;
        }

        Objects.requireNonNull(comparator, "comparator");

        final int rowCount = matrix.length;
        final int colCount = matrix[0].length;

        for (final T[] row : matrix) {
            if (row == null) {
                throw new IllegalArgumentException("Matrix must not contain null rows");
            }
            if (row.length != colCount) {
                throw new IllegalArgumentException("Matrix must be rectangular (not jagged)");
            }
        }

        int rowIndex = 0;
        int colIndex = colCount - 1;

        while (rowIndex < rowCount) {
            if (colIndex < 0) {
                break;
            }
            final T value = matrix[rowIndex][colIndex];
            final int comparison = comparator.compare(value, target);
            if (comparison == 0) {
                return true;
            }
            if (comparison > 0) {
                colIndex--;
            } else {
                rowIndex++;
            }
        }

        return false;
    }

    /**
     * Searches a matrix that is sorted ascending by row and by column.
     *
     * <p>Returns {@code true} if {@code target} exists in the matrix, {@code false} otherwise.
     *
     * @param matrix the input matrix
     * @param target the value to locate
     * @return whether the target exists in the matrix
     * @throws IllegalArgumentException if the matrix is jagged or contains null rows
     */
    public static boolean search(final int[][] matrix, final int target) {
        if (matrix == null) {
            return false;
        }
        if (matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null) {
            return false;
        }
        if (matrix[0].length == 0) {
            return false;
        }

        final int rowCount = matrix.length;
        final int colCount = matrix[0].length;

        for (final int[] row : matrix) {
            if (row == null) {
                throw new IllegalArgumentException("Matrix must not contain null rows");
            }
            if (row.length != colCount) {
                throw new IllegalArgumentException("Matrix must be rectangular (not jagged)");
            }
        }

        int rowIndex = 0;
        int colIndex = colCount - 1;

        while (rowIndex < rowCount) {
            if (colIndex < 0) {
                break;
            }
            final int value = matrix[rowIndex][colIndex];
            if (value == target) {
                return true;
            }
            if (value > target) {
                colIndex--;
            } else {
                rowIndex++;
            }
        }

        return false;
    }
}
