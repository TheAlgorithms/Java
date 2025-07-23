package com.thealgorithms.datastructures.bag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.datastructures.bags.Bag;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class BagTest {

    @Test
    void testBagOperations() {
        Bag<String> bag = new Bag<>();
        assertTrue(bag.isEmpty(), "Bag should be empty initially");
        assertEquals(0, bag.size(), "Bag size should be 0 initially");

        bag.add("item1");
        bag.add("item2");
        bag.add("item1"); // adding duplicate item

        assertFalse(bag.isEmpty(), "Bag should not be empty after adding elements");
        assertEquals(3, bag.size(), "Bag size should be 3 after adding 3 elements");

        assertTrue(bag.contains("item1"), "Bag should contain 'item1'");
        assertTrue(bag.contains("item2"), "Bag should contain 'item2'");
        assertFalse(bag.contains("item3"), "Bag should not contain 'item3'");
        assertFalse(bag.contains(null), "Bag should not contain null");

        // Test iteration
        int count = 0;
        for (String item : bag) {
            assertTrue(item.equals("item1") || item.equals("item2"), "Item should be either 'item1' or 'item2'");
            count++;
        }
        assertEquals(3, count, "Iterator should traverse all 3 items");
    }

    @Test
    void testBagInitialization() {
        Bag<String> bag = new Bag<>();
        assertTrue(bag.isEmpty(), "Bag should be empty initially");
        assertEquals(0, bag.size(), "Bag size should be 0 initially");
    }

    @Test
    void testAddElements() {
        Bag<String> bag = new Bag<>();
        bag.add("item1");
        bag.add("item2");
        bag.add("item1"); // Adding duplicate item

        assertFalse(bag.isEmpty(), "Bag should not be empty after adding elements");
        assertEquals(3, bag.size(), "Bag size should be 3 after adding 3 elements");
    }

    @Test
    void testContainsMethod() {
        Bag<String> bag = new Bag<>();
        bag.add("item1");
        bag.add("item2");

        assertTrue(bag.contains("item1"), "Bag should contain 'item1'");
        assertTrue(bag.contains("item2"), "Bag should contain 'item2'");
        assertFalse(bag.contains("item3"), "Bag should not contain 'item3'");
        assertFalse(bag.contains(null), "Bag should not contain null");
    }

    @Test
    void testContainsAfterAdditions() {
        Bag<String> bag = new Bag<>();
        bag.add("item1");
        bag.add("item2");
        assertTrue(bag.contains("item1"), "Bag should contain 'item1' after addition");
        assertTrue(bag.contains("item2"), "Bag should contain 'item2' after addition");
    }

    @Test
    void testIterator() {
        Bag<String> bag = new Bag<>();
        bag.add("item1");
        bag.add("item2");
        bag.add("item3");

        int count = 0;
        for (String item : bag) {
            assertTrue(item.equals("item1") || item.equals("item2") || item.equals("item3"), "Item should be one of 'item1', 'item2', or 'item3'");
            count++;
        }
        assertEquals(3, count, "Iterator should traverse all 3 items");
    }

    @Test
    void testIteratorEmptyBag() {
        Bag<String> bag = new Bag<>();
        int count = 0;
        for (String ignored : bag) {
            org.junit.jupiter.api.Assertions.fail("Iterator should not return any items for an empty bag");
        }
        assertEquals(0, count, "Iterator should not traverse any items in an empty bag");
    }

    @Test
    void testRemoveMethodThrowsException() {
        Bag<String> bag = new Bag<>();
        bag.add("item1");
        Iterator<String> iterator = bag.iterator();
        assertThrows(UnsupportedOperationException.class, iterator::remove, "Remove operation should throw UnsupportedOperationException");
    }

    @Test
    void testMultipleDuplicates() {
        Bag<String> bag = new Bag<>();
        bag.add("item1");
        bag.add("item1");
        bag.add("item1"); // Add three duplicates

        assertEquals(3, bag.size(), "Bag size should be 3 after adding three duplicates");
        assertTrue(bag.contains("item1"), "Bag should contain 'item1'");
    }

    @Test
    void testLargeNumberOfElements() {
        Bag<Integer> bag = new Bag<>();
        for (int i = 0; i < 1000; i++) {
            bag.add(i);
        }
        assertEquals(1000, bag.size(), "Bag should contain 1000 elements");
    }

    @Test
    void testMixedTypeElements() {
        Bag<Object> bag = new Bag<>();
        bag.add("string");
        bag.add(1);
        bag.add(2.0);

        assertTrue(bag.contains("string"), "Bag should contain a string");
        assertTrue(bag.contains(1), "Bag should contain an integer");
        assertTrue(bag.contains(2.0), "Bag should contain a double");
    }

    @Test
    void testIteratorWithDuplicates() {
        Bag<String> bag = new Bag<>();
        bag.add("item1");
        bag.add("item1");
        bag.add("item2");

        int count = 0;
        for (String item : bag) {
            assertTrue(item.equals("item1") || item.equals("item2"), "Item should be either 'item1' or 'item2'");
            count++;
        }
        assertEquals(3, count, "Iterator should traverse all 3 items including duplicates");
    }

    @Test
    void testCollectionElements() {
        Bag<List<String>> bag = new Bag<>();
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");

        List<String> list2 = new ArrayList<>();
        list2.add("c");

        List<String> emptyList = new ArrayList<>();

        bag.add(list1);
        bag.add(list2);
        bag.add(emptyList);
        bag.add(list1); // Duplicate

        assertEquals(4, bag.size(), "Bag should contain 4 list elements");
        assertTrue(bag.contains(list1), "Bag should contain list1");
        assertTrue(bag.contains(list2), "Bag should contain list2");
        assertTrue(bag.contains(emptyList), "Bag should contain empty list");
    }

    @Test
    void testIteratorConsistency() {
        Bag<String> bag = new Bag<>();
        bag.add("first");
        bag.add("second");
        bag.add("third");

        // Multiple iterations should return same elements
        List<String> firstIteration = new ArrayList<>();
        for (String item : bag) {
            firstIteration.add(item);
        }

        List<String> secondIteration = new ArrayList<>();
        for (String item : bag) {
            secondIteration.add(item);
        }

        assertEquals(firstIteration.size(), secondIteration.size(), "Both iterations should have same size");
        assertEquals(3, firstIteration.size(), "First iteration should have 3 elements");
        assertEquals(3, secondIteration.size(), "Second iteration should have 3 elements");
    }

    @Test
    void testMultipleIterators() {
        Bag<String> bag = new Bag<>();
        bag.add("item1");
        bag.add("item2");

        Iterator<String> iter1 = bag.iterator();
        Iterator<String> iter2 = bag.iterator();

        assertTrue(iter1.hasNext(), "First iterator should have next element");
        assertTrue(iter2.hasNext(), "Second iterator should have next element");

        String first1 = iter1.next();
        String first2 = iter2.next();

        org.junit.jupiter.api.Assertions.assertNotNull(first1, "First iterator should return non-null element");
        org.junit.jupiter.api.Assertions.assertNotNull(first2, "Second iterator should return non-null element");
    }

    @Test
    void testIteratorHasNextConsistency() {
        Bag<String> bag = new Bag<>();
        bag.add("single");

        Iterator<String> iter = bag.iterator();
        assertTrue(iter.hasNext(), "hasNext should return true");
        assertTrue(iter.hasNext(), "hasNext should still return true after multiple calls");

        String item = iter.next();
        assertEquals("single", item, "Next should return the single item");

        assertFalse(iter.hasNext(), "hasNext should return false after consuming element");
        assertFalse(iter.hasNext(), "hasNext should still return false");
    }

    @Test
    void testIteratorNextOnEmptyBag() {
        Bag<String> bag = new Bag<>();
        Iterator<String> iter = bag.iterator();

        assertFalse(iter.hasNext(), "hasNext should return false for empty bag");
        assertThrows(NoSuchElementException.class, iter::next, "next() should throw NoSuchElementException on empty bag");
    }

    @Test
    void testBagOrderIndependence() {
        Bag<String> bag1 = new Bag<>();
        Bag<String> bag2 = new Bag<>();

        // Add same elements in different order
        bag1.add("first");
        bag1.add("second");
        bag1.add("third");

        bag2.add("third");
        bag2.add("first");
        bag2.add("second");

        assertEquals(bag1.size(), bag2.size(), "Bags should have same size");

        // Both bags should contain all elements
        assertTrue(bag1.contains("first") && bag2.contains("first"));
        assertTrue(bag1.contains("second") && bag2.contains("second"));
        assertTrue(bag1.contains("third") && bag2.contains("third"));
    }
}
