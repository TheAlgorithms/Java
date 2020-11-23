package com.sorts;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StrandSortTest {
    StrandSort strandSort = new StrandSort();

    @Test
    public <T extends Comparable<T>> void testStrandSortEmpty() {
        List<T> unsorted = Collections.emptyList();
        List<T> sorted = strandSort.strandSort(unsorted);
        List<T> expected = Collections.emptyList();
        assertEquals("Incorrect result from method.", expected, sorted);
    }

    @Test
    public void testStrandSortSingleInteger() {
        List<Integer> unsorted = new ArrayList<>();
        unsorted.add(2);
        List<Integer> sorted = strandSort.strandSort(unsorted);
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        assertEquals("Incorrect result from method.", expected, sorted);
    }

    @Test
    public void testStrandSortSingleCharacter() {
        List<Character> unsorted = new ArrayList<>();
        unsorted.add('a');
        List<Character> sorted = strandSort.strandSort(unsorted);
        List<Character> expected = new ArrayList<>();
        expected.add('a');
        assertEquals("Incorrect result from method.", expected, sorted);
    }

    @Test
    public void testStrandSortSingleString() {
        List<String> unsorted = new ArrayList<>();
        unsorted.add("Test");
        List<String> sorted = strandSort.strandSort(unsorted);
        List<String> expected = new ArrayList<>();
        expected.add("Test");
        assertEquals("Incorrect result from method.", expected, sorted);
    }

    @Test
    public void testStrandSortMultipleIntegers() {
        List<Integer> unsorted = new ArrayList<>();
        unsorted.add(30);
        unsorted.add(2);
        unsorted.add(8);
        unsorted.add(7);
        unsorted.add(2);
        unsorted.add(4);
        unsorted.add(6);
        unsorted.add(2);
        unsorted.add(2);
        unsorted.add(3);
        List<Integer> sorted = strandSort.strandSort(unsorted);
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(2);
        expected.add(2);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(6);
        expected.add(7);
        expected.add(8);
        expected.add(30);
        assertEquals("Incorrect result from method.", expected, sorted);
    }

    @Test
    public void testStrandSortMultipleNegativeIntegers() {
        List<Integer> unsorted = new ArrayList<>();
        unsorted.add(-5);
        unsorted.add(30);
        unsorted.add(2);
        unsorted.add(8);
        unsorted.add(7);
        unsorted.add(-8);
        unsorted.add(2);
        unsorted.add(3);
        unsorted.add(4);
        List<Integer> sorted = strandSort.strandSort(unsorted);
        List<Integer> expected = new ArrayList<>();
        expected.add(-8);
        expected.add(-5);
        expected.add(2);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(7);
        expected.add(8);
        expected.add(30);
        assertEquals("Incorrect result from method.", expected, sorted);
    }

    @Test
    public void testStrandSortMultipleCharacters() {
        List<Character> unsorted = new ArrayList<>();
        unsorted.add('b');
        unsorted.add('c');
        unsorted.add('g');
        unsorted.add('a');
        unsorted.add('z');
        unsorted.add('y');
        List<Character> sorted = strandSort.strandSort(unsorted);
        List<Character> expected = new ArrayList<>();
        expected.add('a');
        expected.add('b');
        expected.add('c');
        expected.add('g');
        expected.add('y');
        expected.add('z');
        assertEquals("Incorrect result from method.", expected, sorted);
    }

    @Test
    public void testStrandSortMultipleStrings() {
        List<String> unsorted = new ArrayList<>();
        unsorted.add("Cantaloupe");
        unsorted.add("banana");
        unsorted.add("candy");
        unsorted.add("Green");
        unsorted.add("apple");
        unsorted.add("zebra");
        unsorted.add("Apple");
        List<String> sorted = strandSort.strandSort(unsorted);
        List<String> expected = new ArrayList<>();
        expected.add("Apple");
        expected.add("Cantaloupe");
        expected.add("Green");
        expected.add("apple");
        expected.add("banana");
        expected.add("candy");
        expected.add("zebra");
        assertEquals("Incorrect result from method.", expected, sorted);
    }
}
