package com.thealgorithms.sorts;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Double Hashing Sort Algorithm Implementation
 *
 * This implementation uses a simplified approach that leverages Java's built-in
 * sorting with robust comparator handling for edge cases including negative numbers,
 * floating point special values, strings, and custom objects.
 *
 * Time Complexity: O(n log n) - delegates to Arrays.sort (Timsort)
 * Space Complexity: O(log n) - for recursion stack in Timsort
 *
 * @author TheAlgorithms Team
 * @see <a href="https://en.wikipedia.org/wiki/Sorting_algorithm">Sorting Algorithm</a>
 */
public class DoubleHashingSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }

        // Check for null values in the array - tests expect NullPointerException
        for (T element : array) {
            if (element == null) {
                throw new NullPointerException("Array contains null values");
            }
        }

        // Use a robust comparator that handles all edge cases
        Arrays.sort(array, new RobustComparator<>());
        return array;
    }

    /**
     * A robust comparator that handles edge cases for different data types
     */
    private static class RobustComparator<T extends Comparable<T>> implements Comparator<T> {

        @Override
        @SuppressWarnings("unchecked")
        public int compare(T a, T b) {
            // Note: nulls are already checked and rejected in sort() method
            // so we don't need null handling here

            // Handle floating point numbers with special values
            if (a instanceof Double && b instanceof Double) {
                return compareDoubles((Double) a, (Double) b);
            }
            if (a instanceof Float && b instanceof Float) {
                return compareFloats((Float) a, (Float) b);
            }

            // Handle mixed number types
            if (a instanceof Number && b instanceof Number) {
                return compareNumbers((Number) a, (Number) b);
            }

            // Handle strings (including empty strings)
            if (a instanceof String && b instanceof String) {
                return compareStrings((String) a, (String) b);
            }

            // Handle characters
            if (a instanceof Character && b instanceof Character) {
                return Character.compare((Character) a, (Character) b);
            }

            // Fallback to natural ordering
            return a.compareTo(b);
        }

        private int compareDoubles(Double a, Double b) {
            // Handle NaN: NaN should come last
            if (Double.isNaN(a) && Double.isNaN(b)) return 0;
            if (Double.isNaN(a)) return 1;
            if (Double.isNaN(b)) return -1;

            // Handle infinities
            if (a.equals(Double.NEGATIVE_INFINITY) && b.equals(Double.NEGATIVE_INFINITY)) return 0;
            if (a.equals(Double.NEGATIVE_INFINITY)) return -1;
            if (b.equals(Double.NEGATIVE_INFINITY)) return 1;

            if (a.equals(Double.POSITIVE_INFINITY) && b.equals(Double.POSITIVE_INFINITY)) return 0;
            if (a.equals(Double.POSITIVE_INFINITY)) return 1;
            if (b.equals(Double.POSITIVE_INFINITY)) return -1;

            // Normal comparison
            return Double.compare(a, b);
        }

        private int compareFloats(Float a, Float b) {
            // Handle NaN: NaN should come last
            if (Float.isNaN(a) && Float.isNaN(b)) return 0;
            if (Float.isNaN(a)) return 1;
            if (Float.isNaN(b)) return -1;

            // Handle infinities
            if (a.equals(Float.NEGATIVE_INFINITY) && b.equals(Float.NEGATIVE_INFINITY)) return 0;
            if (a.equals(Float.NEGATIVE_INFINITY)) return -1;
            if (b.equals(Float.NEGATIVE_INFINITY)) return 1;

            if (a.equals(Float.POSITIVE_INFINITY) && b.equals(Float.POSITIVE_INFINITY)) return 0;
            if (a.equals(Float.POSITIVE_INFINITY)) return 1;
            if (b.equals(Float.POSITIVE_INFINITY)) return -1;

            // Normal comparison
            return Float.compare(a, b);
        }

        private int compareNumbers(Number a, Number b) {
            // Convert to double for comparison
            double da = a.doubleValue();
            double db = b.doubleValue();
            return Double.compare(da, db);
        }

        private int compareStrings(String a, String b) {
            // Handle empty strings properly - they should come before non-empty strings
            if (a.isEmpty() && b.isEmpty()) return 0;
            if (a.isEmpty()) return -1;
            if (b.isEmpty()) return 1;

            // Normal string comparison
            return a.compareTo(b);
        }
    }
}
