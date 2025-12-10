package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Objects;
import org.junit.jupiter.api.Test;

public class AdaptiveMergeSortTest {

    @Test
    public void testSortIntegers() {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
        Integer[] input = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        Integer[] expected = {1, 4, 6, 9, 12, 23, 54, 78, 231};
        Integer[] result = adaptiveMergeSort.sort(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortStrings() {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
        String[] input = {"c", "a", "e", "b", "d"};
        String[] expected = {"a", "b", "c", "d", "e"};
        String[] result = adaptiveMergeSort.sort(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortWithDuplicates() {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
        Integer[] input = {1, 3, 2, 2, 5, 4};
        Integer[] expected = {1, 2, 2, 3, 4, 5};
        Integer[] result = adaptiveMergeSort.sort(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortEmptyArray() {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
        Integer[] input = {};
        Integer[] expected = {};
        Integer[] result = adaptiveMergeSort.sort(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortSingleElement() {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
        Integer[] input = {42};
        Integer[] expected = {42};
        Integer[] result = adaptiveMergeSort.sort(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSortAlreadySortedArray() {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
        Integer[] inputArray = {-12, -6, -3, 0, 2, 2, 13, 46};
        Integer[] outputArray = adaptiveMergeSort.sort(inputArray);
        Integer[] expectedOutput = {-12, -6, -3, 0, 2, 2, 13, 46};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void testSortReversedSortedArray() {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
        Integer[] inputArray = {46, 13, 2, 2, 0, -3, -6, -12};
        Integer[] outputArray = adaptiveMergeSort.sort(inputArray);
        Integer[] expectedOutput = {-12, -6, -3, 0, 2, 2, 13, 46};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void testSortAllEqualArray() {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
        Integer[] inputArray = {2, 2, 2, 2, 2};
        Integer[] outputArray = adaptiveMergeSort.sort(inputArray);
        Integer[] expectedOutput = {2, 2, 2, 2, 2};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void testSortMixedCaseStrings() {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
        String[] inputArray = {"banana", "Apple", "apple", "Banana"};
        String[] expectedOutput = {"Apple", "Banana", "apple", "banana"};
        String[] outputArray = adaptiveMergeSort.sort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }

    /**
     * Custom Comparable class for testing.
     **/
    static class Person implements Comparable<Person> {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(this.age, o.age);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }

    @Test
    public void testSortCustomObjects() {
        AdaptiveMergeSort adaptiveMergeSort = new AdaptiveMergeSort();
        Person[] inputArray = {
            new Person("Alice", 32),
            new Person("Bob", 25),
            new Person("Charlie", 28),
        };
        Person[] expectedOutput = {
            new Person("Bob", 25),
            new Person("Charlie", 28),
            new Person("Alice", 32),
        };
        Person[] outputArray = adaptiveMergeSort.sort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }
}
