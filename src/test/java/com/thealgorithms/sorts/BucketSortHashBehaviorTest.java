package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class BucketSortHashBehaviorTest {

    private static <T extends Comparable<T>> int pseudoHash(final T element, final T min, final T max, final int numberOfBuckets) {
        // Reproduces the production hash() logic
        double range = max.compareTo(min);
        double normalizedValue = element.compareTo(min) / range; // -1/0/1 divided by -1/0/1
        return (int) (normalizedValue * (numberOfBuckets - 1));
    }

    @Test // Test case when all numbers are equal
    void sortStillCorrectWhenAllEqual() {
        Integer[] arr = {1, 1, 1, 1, 1};
        Integer[] expected = arr.clone();

        new BucketSort().sort(arr);
        assertArrayEquals(expected, arr);

        // Observe bucket mapping (all collapse to index 0)
        Integer min = 1, 
        Integer max = 1;
        int numberOfBuckets = Math.max(arr.length / 10, 1); // same as BUCKET_DIVISOR rule
        int idx = pseudoHash(1, min, max, numberOfBuckets);
        // idx will be 0 because NaN cast to int -> 0 in Java
        System.out.println("All-equal case -> bucket index: " + idx);
    }

    @Test // Test case with non-equal integers
    void sortStillCorrectNonEqualIntegers() {
        Integer[] arr = {20, 40, 30, 10};
        Integer[] expected = {10, 20, 30, 40};

        new BucketSort().sort(arr);
        assertArrayEquals(expected, arr);

        Integer min = Arrays.stream(arr).min(Integer::compareTo).get();
        Integer max = Arrays.stream(arr).max(Integer::compareTo).get();
        int numberOfBuckets = Math.max(arr.length / 10, 1); // often 1 here; bump to 4 to demonstrate
        numberOfBuckets = 4;

        for (Integer x : arr) {
            int idx = pseudoHash(x, min, max, numberOfBuckets);
            System.out.println("Value " + x + " -> bucket " + idx);
        }
        // Expect only two distinct buckets because compareTo gives -1/0/1
    }

    @Test // Test case when the Array contains Strings
    void sortStillCorrectWhenStrings() {
        String[] arr = {"apple", "banana", "carrot"};
        String[] expected = arr.clone();

        new BucketSort().sort(arr);
        assertArrayEquals(expected, arr);

        String min = Arrays.stream(arr).min(String::compareTo).get();
        String max = Arrays.stream(arr).max(String::compareTo).get();
        int numberOfBuckets = 4;

        for (String s : arr) {
            int idx = pseudoHash(s, min, max, numberOfBuckets);
            System.out.println("Value \"" + s + "\" -> bucket " + idx);
        }
        // Buckets reflect only lexicographic order, not a numeric spacing
    }
}
