package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Objects;
import org.junit.jupiter.api.Test;

public class BogoSortTest {

    private BogoSort bogoSort = new BogoSort();

    @Test
    public void bogoSortEmptyArray() {
        Integer[] inputArray = {};
        Integer[] outputArray = bogoSort.sort(inputArray);
        Integer[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortSingleIntegerArray() {
        Integer[] inputArray = {4};
        Integer[] outputArray = bogoSort.sort(inputArray);
        Integer[] expectedOutput = {4};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortSingleStringArray() {
        String[] inputArray = {"s"};
        String[] outputArray = bogoSort.sort(inputArray);
        String[] expectedOutput = {"s"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortNonDuplicateIntegerArray() {
        Integer[] inputArray = {6, -1, 99, 27, -15, 23, -36};
        Integer[] outputArray = bogoSort.sort(inputArray);
        Integer[] expectedOutput = {-36, -15, -1, 6, 23, 27, 99};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortDuplicateIntegerArray() {
        Integer[] inputArray = {6, -1, 27, -15, 23, 27, -36, 23};
        Integer[] outputArray = bogoSort.sort(inputArray);
        Integer[] expectedOutput = {-36, -15, -1, 6, 23, 23, 27, 27};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortNonDuplicateStringArray() {
        String[] inputArray = {"s", "b", "k", "a", "d", "c", "h"};
        String[] outputArray = bogoSort.sort(inputArray);
        String[] expectedOutput = {"a", "b", "c", "d", "h", "k", "s"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortDuplicateStringArray() {
        String[] inputArray = {"s", "b", "d", "a", "d", "c", "h", "b"};
        String[] outputArray = bogoSort.sort(inputArray);
        String[] expectedOutput = {"a", "b", "b", "c", "d", "d", "h", "s"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortAlreadySortedArray() {
        Integer[] inputArray = {-12, -6, -3, 0, 2, 2, 13, 46};
        Integer[] outputArray = bogoSort.sort(inputArray);
        Integer[] expectedOutput = {-12, -6, -3, 0, 2, 2, 13, 46};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortReversedSortedArray() {
        Integer[] inputArray = {46, 13, 2, 2, 0, -3, -6, -12};
        Integer[] outputArray = bogoSort.sort(inputArray);
        Integer[] expectedOutput = {-12, -6, -3, 0, 2, 2, 13, 46};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortAllEqualArray() {
        Integer[] inputArray = {2, 2, 2, 2, 2};
        Integer[] outputArray = bogoSort.sort(inputArray);
        Integer[] expectedOutput = {2, 2, 2, 2, 2};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bogoSortMixedCaseStrings() {
        String[] inputArray = {"banana", "Apple", "apple", "Banana"};
        String[] expectedOutput = {"Apple", "Banana", "apple", "banana"};
        String[] outputArray = bogoSort.sort(inputArray);
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
    public void bogoSortCustomObjects() {
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
        Person[] outputArray = bogoSort.sort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }
}
