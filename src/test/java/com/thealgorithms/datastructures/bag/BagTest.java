package com.thealgorithms.datastructures.bag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.datastructures.bags.Bag;
import java.util.Iterator;
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
}
