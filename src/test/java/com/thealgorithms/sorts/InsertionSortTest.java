package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import java.util.function.Function;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InsertionSortTest {
    private InsertionSort insertionSort;

    @BeforeEach
    void setUp() {
        insertionSort = new InsertionSort();
    }

    @Test
    void insertionSortSortEmptyArrayShouldPass() {
        testEmptyArray(insertionSort::sort);
        testEmptyArray(insertionSort::sentinelSort);
    }

    private void testEmptyArray(Function<Integer[], Integer[]> sortAlgorithm) {
        Integer[] array = {};
        Integer[] sorted = sortAlgorithm.apply(array);
        Integer[] expected = {};
        assertArrayEquals(expected, sorted);
        assertTrue(SortUtils.isSorted(sorted));
    }

    @Test
    void insertionSortClassicalSortSingleValueArrayShouldPass() {
        testSingleValue(insertionSort::sort);
        testSingleValue(insertionSort::sentinelSort);
    }

    private void testSingleValue(Function<Integer[], Integer[]> sortAlgorithm) {
        Integer[] array = {7};
        Integer[] sorted = sortAlgorithm.apply(array);
        Integer[] expected = {7};
        assertArrayEquals(expected, sorted);
        assertTrue(SortUtils.isSorted(sorted));
    }

    @Test
    void insertionSortClassicalWithIntegerArrayShouldPass() {
        testIntegerArray(insertionSort::sort);
        testIntegerArray(insertionSort::sentinelSort);
    }

    private void testIntegerArray(Function<Integer[], Integer[]> sortAlgorithm) {
        Integer[] array = {49, 4, 36, 9, 144, 1};
        Integer[] sorted = sortAlgorithm.apply(array);
        Integer[] expected = {1, 4, 9, 36, 49, 144};
        assertArrayEquals(expected, sorted);
        assertTrue(SortUtils.isSorted(sorted));
    }

    @Test
    void insertionSortClassicalForArrayWithNegativeValuesShouldPass() {
        testWithNegativeValues(insertionSort::sort);
        testWithNegativeValues(insertionSort::sentinelSort);
    }

    private void testWithNegativeValues(Function<Integer[], Integer[]> sortAlgorithm) {
        Integer[] array = {49, -36, -144, -49, 1, 9};
        Integer[] sorted = sortAlgorithm.apply(array);
        Integer[] expected = {-144, -49, -36, 1, 9, 49};
        assertArrayEquals(expected, sorted);
        assertTrue(SortUtils.isSorted(sorted));
    }

    @Test
    void insertionSortClassicalForArrayWithDuplicateValuesShouldPass() {
        testWithDuplicates(insertionSort::sort);
        testWithDuplicates(insertionSort::sentinelSort);
    }

    private void testWithDuplicates(Function<Integer[], Integer[]> sortAlgorithm) {
        Integer[] array = {36, 1, 49, 1, 4, 9};
        Integer[] sorted = sortAlgorithm.apply(array);
        Integer[] expected = {1, 1, 4, 9, 36, 49};
        assertArrayEquals(expected, sorted);
        assertTrue(SortUtils.isSorted(sorted));
    }

    @Test
    void insertionSortClassicalWithStringArrayShouldPass() {
        testWithStringArray(insertionSort::sort);
        testWithStringArray(insertionSort::sentinelSort);
    }

    private void testWithStringArray(Function<String[], String[]> sortAlgorithm) {
        String[] array = {"c", "a", "e", "b", "d"};
        String[] sorted = sortAlgorithm.apply(array);
        String[] expected = {"a", "b", "c", "d", "e"};
        assertArrayEquals(expected, sorted);
        assertTrue(SortUtils.isSorted(sorted));
    }

    @Test
    void insertionSortClassicalWithRandomArrayPass() {
        testWithRandomArray(insertionSort::sort);
        testWithRandomArray(insertionSort::sentinelSort);
    }

    private void testWithRandomArray(Function<Double[], Double[]> sortAlgorithm) {
        int randomSize = SortUtilsRandomGenerator.generateInt(10_000);
        Double[] array = SortUtilsRandomGenerator.generateArray(randomSize);
        Double[] sorted = sortAlgorithm.apply(array);
        assertTrue(SortUtils.isSorted(sorted));
    }

    @Test
    public void testSortAlreadySortedArray() {
        Integer[] inputArray = {-12, -6, -3, 0, 2, 2, 13, 46};
        Integer[] outputArray = insertionSort.sort(inputArray);
        Integer[] expectedOutput = {-12, -6, -3, 0, 2, 2, 13, 46};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void testSortReversedSortedArray() {
        Integer[] inputArray = {46, 13, 2, 2, 0, -3, -6, -12};
        Integer[] outputArray = insertionSort.sort(inputArray);
        Integer[] expectedOutput = {-12, -6, -3, 0, 2, 2, 13, 46};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void testSortAllEqualArray() {
        Integer[] inputArray = {2, 2, 2, 2, 2};
        Integer[] outputArray = insertionSort.sort(inputArray);
        Integer[] expectedOutput = {2, 2, 2, 2, 2};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void testSortMixedCaseStrings() {
        String[] inputArray = {"banana", "Apple", "apple", "Banana"};
        String[] expectedOutput = {"Apple", "Banana", "apple", "banana"};
        String[] outputArray = insertionSort.sort(inputArray);
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
        Person[] outputArray = insertionSort.sort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }
}
