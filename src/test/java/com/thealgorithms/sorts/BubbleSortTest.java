package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * @author Aitor Fidalgo (https://github.com/aitorfi)
 * @see BubbleSort
 */
public class BubbleSortTest {

    private BubbleSort bubbleSort = new BubbleSort();

    @Test
    public void bubbleSortEmptyArray() {
        Integer[] inputArray = {};
        Integer[] outputArray = bubbleSort.sort(inputArray);
        Integer[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bubbleSortSingleIntegerElementArray() {
        Integer[] inputArray = {4};
        Integer[] outputArray = bubbleSort.sort(inputArray);
        Integer[] expectedOutput = {4};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bubbleSortSingleStringElementArray() {
        String[] inputArray = {"s"};
        String[] outputArray = bubbleSort.sort(inputArray);
        String[] expectedOutput = {"s"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bubbleSortIntegerArray() {
        Integer[] inputArray = {4, 23, -6, 78, 1, 54, 23, -6, -231, 9, 12};
        Integer[] outputArray = bubbleSort.sort(inputArray);
        Integer[] expectedOutput = {
            -231,
            -6,
            -6,
            1,
            4,
            9,
            12,
            23,
            23,
            54,
            78,
        };
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bubbleSortStringArray() {
        String[] inputArray = {
            "cbf",
            "auk",
            "ó",
            "(b",
            "a",
            ")",
            "au",
            "á",
            "cba",
            "auk",
            "(a",
            "bhy",
            "cba",
        };
        String[] outputArray = bubbleSort.sort(inputArray);
        String[] expectedOutput = {
            "(a",
            "(b",
            ")",
            "a",
            "au",
            "auk",
            "auk",
            "bhy",
            "cba",
            "cba",
            "cbf",
            "á",
            "ó",
        };
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bubbleSortAlreadySortedArray() {
        Integer[] inputArray = {-12, -6, -3, 0, 2, 2, 13, 46};
        Integer[] outputArray = bubbleSort.sort(inputArray);
        Integer[] expectedOutput = {-12, -6, -3, 0, 2, 2, 13, 46};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bubbleSortReversedSortedArray() {
        Integer[] inputArray = {46, 13, 2, 2, 0, -3, -6, -12};
        Integer[] outputArray = bubbleSort.sort(inputArray);
        Integer[] expectedOutput = {-12, -6, -3, 0, 2, 2, 13, 46};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bubbleSortAllEqualArray() {
        Integer[] inputArray = {2, 2, 2, 2, 2};
        Integer[] outputArray = bubbleSort.sort(inputArray);
        Integer[] expectedOutput = {2, 2, 2, 2, 2};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void bubbleSortMixedCaseStrings() {
        String[] inputArray = {"banana", "Apple", "apple", "Banana"};
        String[] expectedOutput = {"Apple", "Banana", "apple", "banana"};
        String[] outputArray = bubbleSort.sort(inputArray);
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
    public void bubbleSortCustomObjects() {
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
        Person[] outputArray = bubbleSort.sort(inputArray);
        assertArrayEquals(expectedOutput, outputArray);
    }
}
