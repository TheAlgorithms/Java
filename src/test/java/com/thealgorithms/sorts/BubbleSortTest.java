package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for BubbleSort implementation.
 *
 * @author Aitor Fidalgo
 * @see BubbleSort
 */
class BubbleSortTest {

    private final BubbleSort bubbleSort = new BubbleSort();

    @Test
    @DisplayName("BubbleSort should handle empty array")
    void bubbleSortEmptyArray() {
        Integer[] inputArray = {};
        Integer[] outputArray = bubbleSort.sort(inputArray);
        assertArrayEquals(new Integer[] {}, outputArray);
    }

    @Test
    @DisplayName("BubbleSort should handle single integer element")
    void bubbleSortSingleIntegerElementArray() {
        Integer[] inputArray = {4};
        Integer[] outputArray = bubbleSort.sort(inputArray);
        assertArrayEquals(new Integer[] {4}, outputArray);
    }

    @Test
    @DisplayName("BubbleSort should handle single string element")
    void bubbleSortSingleStringElementArray() {
        String[] inputArray = {"s"};
        String[] outputArray = bubbleSort.sort(inputArray);
        assertArrayEquals(new String[] {"s"}, outputArray);
    }

    @Test
    @DisplayName("BubbleSort should sort integer array with negatives and duplicates")
    void bubbleSortIntegerArray() {
        Integer[] inputArray = {4, 23, -6, 78, 1, 54, 23, -6, -231, 9, 12};
        Integer[] outputArray = bubbleSort.sort(inputArray);
        Integer[] expectedOutput = {
            -231, -6, -6, 1, 4, 9, 12, 23, 23, 54, 78
        };
        assertArrayEquals(expectedOutput, outputArray);
    }

    @Test
    @DisplayName("BubbleSort should sort string array")
    void bubbleSortStringArray() {
        String[] inputArray = {
            "cbf", "auk", "ó", "(b", "a", ")", "au",
            "á", "cba", "auk", "(a", "bhy", "cba"
        };
        String[] expectedOutput = {
            "(a", "(b", ")", "a", "au", "auk", "auk",
            "bhy", "cba", "cba", "cbf", "á", "ó"
        };
        String[] outputArray = bubbleSort.sort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }

    @Test
    @DisplayName("BubbleSort should handle already sorted array")
    void bubbleSortAlreadySortedArray() {
        Integer[] inputArray = {-12, -6, -3, 0, 2, 2, 13, 46};
        Integer[] outputArray = bubbleSort.sort(inputArray);
        assertArrayEquals(inputArray, outputArray);
    }

    @Test
    @DisplayName("BubbleSort should sort reverse ordered array")
    void bubbleSortReversedSortedArray() {
        Integer[] inputArray = {46, 13, 2, 2, 0, -3, -6, -12};
        Integer[] expectedOutput = {-12, -6, -3, 0, 2, 2, 13, 46};
        Integer[] outputArray = bubbleSort.sort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }

    @Test
    @DisplayName("BubbleSort should handle array with all equal elements")
    void bubbleSortAllEqualArray() {
        Integer[] inputArray = {2, 2, 2, 2, 2};
        Integer[] outputArray = bubbleSort.sort(inputArray);
        assertArrayEquals(inputArray, outputArray);
    }

    @Test
    @DisplayName("BubbleSort should sort mixed case strings using natural order")
    void bubbleSortMixedCaseStrings() {
        String[] inputArray = {"banana", "Apple", "apple", "Banana"};
        String[] expectedOutput = {"Apple", "Banana", "apple", "banana"};
        String[] outputArray = bubbleSort.sort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }

    @Test
    @DisplayName("BubbleSort should sort negative numbers correctly")
    void bubbleSortNegativeNumbers() {
        Integer[] inputArray = {5, -1, 7, 0};
        Integer[] expectedOutput = {-1, 0, 5, 7};
        Integer[] outputArray = bubbleSort.sort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }

    @Test
    @DisplayName("BubbleSort should be stable for equal elements")
    void bubbleSortStabilityTest() {
        Person[] inputArray = {
            new Person("Alice", 25),
            new Person("Bob", 25),
            new Person("Charlie", 30),
        };

        Person[] outputArray = bubbleSort.sort(inputArray);

        assertEquals("Alice", outputArray[0].name);
        assertEquals("Bob", outputArray[1].name);
    }

    @Test
    @DisplayName("BubbleSort should throw exception for null input")
    void bubbleSortNullArray() {
        assertThrows(
            IllegalArgumentException.class,
            () -> bubbleSort.sort(null)
        );
    }

    /**
     * Custom Comparable class for testing stability.
     */
    static class Person implements Comparable<Person> {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person other) {
            return Integer.compare(this.age, other.age);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person)) return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }
}
