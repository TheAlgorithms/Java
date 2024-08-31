package com.thealgorithms.datastructures.bag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    void testContainsAfterRemoveOperation() {
        Bag<String> bag = new Bag<>();
        bag.add("item1");
        bag.add("item2");
        assertTrue(bag.contains("item1"), "Bag should contain 'item1' before removal");
        assertTrue(bag.contains("item2"), "Bag should contain 'item2' before removal");
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
        org.junit.jupiter.api.Assertions.assertThrows(UnsupportedOperationException.class, iterator::remove, "Remove operation should throw UnsupportedOperationException");
    }
}
