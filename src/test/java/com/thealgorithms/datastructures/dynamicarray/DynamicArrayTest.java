package com.thealgorithms.datastructures.dynamicarray;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DynamicArrayTest {

    private DynamicArray<String> array;

    @BeforeEach
    public void setUp() {
        array = new DynamicArray<>();
    }

    @Test
    public void testGetElement() {
        array.add("Alice");
        array.add("Bob");
        array.add("Charlie");
        array.add("David");
        assertEquals("Bob", array.get(1));
    }

    @Test
    public void testGetInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(10));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(100));
    }

    @Test
    public void testAddElement() {
        array.add("Alice");
        array.add("Bob");
        assertEquals(2, array.getSize());
        assertEquals("Alice", array.get(0));
        assertEquals("Bob", array.get(1));
    }

    @Test
    public void testAddAndGet() {
        array.add("Alice");
        array.add("Bob");

        assertEquals("Alice", array.get(0));
        assertEquals("Bob", array.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(2));
    }

    @Test
    public void testAddBeyondCapacity() {
        for (int i = 0; i < 20; i++) {
            array.add("Element " + i);
        }
        assertEquals(20, array.getSize());
        assertEquals("Element 19", array.get(19));
    }

    @Test
    public void testPutElement() {
        array.put(5, "Placeholder");
        assertEquals(6, array.getSize());
        assertEquals("Placeholder", array.get(5));
    }

    @Test
    public void testPutElementBeyondCapacity() {
        array.put(20, "FarAway");
        assertEquals(21, array.getSize());
        assertEquals("FarAway", array.get(20));
    }

    @Test
    public void testPutAndDynamicCapacity() {
        array.put(0, "Alice");
        array.put(2, "Bob"); // Tests capacity expansion

        assertEquals("Alice", array.get(0));
        assertEquals("Bob", array.get(2));
        assertEquals(3, array.getSize()); // Size should be 3 due to index 2
    }

    @Test
    public void testRemoveElement() {
        array.add("Alice");
        array.add("Bob");
        String removed = array.remove(0);
        assertEquals("Alice", removed);
        assertEquals(1, array.getSize());
        assertEquals("Bob", array.get(0));
    }

    @Test
    public void testRemoveInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> array.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> array.remove(10));
    }

    @Test
    public void testRemoveComplex() {
        array.add("Alice");
        array.add("Bob");
        array.add("Charlie");

        assertEquals("Bob", array.remove(1));
        assertEquals("Alice", array.get(0));
        assertEquals("Charlie", array.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> array.remove(2));
    }

    @Test
    public void testRemoveEdgeCases() {
        array.add("Alice");
        array.add("Bob");

        assertEquals("Alice", array.remove(0));
        assertEquals(1, array.getSize());
        assertEquals("Bob", array.get(0));

        assertEquals("Bob", array.remove(0));
        assertTrue(array.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(0));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(array.isEmpty());

        array.add("Alice");
        assertFalse(array.isEmpty());

        array.remove(0);
        assertTrue(array.isEmpty());
    }

    @Test
    public void testSize() {
        DynamicArray<String> array = new DynamicArray<>();
        assertEquals(0, array.getSize());

        array.add("Alice");
        array.add("Bob");
        assertEquals(2, array.getSize());

        array.remove(0);
        assertEquals(1, array.getSize());
    }

    @Test
    public void testToString() {
        array.add("Alice");
        array.add("Bob");

        assertEquals("[Alice, Bob]", array.toString());
    }

    @Test
    public void testIterator() {
        array.add("Alice");
        array.add("Bob");

        String result = array.stream().collect(Collectors.joining(", "));
        assertEquals("Alice, Bob", result);
    }

    @Test
    public void testStreamAsString() {
        array.add("Alice");
        array.add("Bob");

        String result = array.stream().collect(Collectors.joining(", "));
        assertEquals("Alice, Bob", result);
    }

    @Test
    public void testStream() {
        array.add("Alice");
        array.add("Bob");
        long count = array.stream().count();
        assertEquals(2, count);
    }

    @Test
    public void testAddToFullCapacity() {
        DynamicArray<String> array = new DynamicArray<>(2);
        array.add("Alice");
        array.add("Bob");
        array.add("Charlie"); // Triggers capacity expansion

        assertEquals(3, array.getSize());
        assertEquals("Charlie", array.get(2));
    }

    @Test
    public void testPutWithNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> array.put(-1, "Alice"));
    }

    @Test
    public void testGetWithNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));
    }

    @Test
    public void testIteratorConcurrentModification() {
        array.add("Alice");
        array.add("Bob");

        Iterator<String> iterator = array.iterator();
        array.add("Charlie"); // Modify during iteration

        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void testIteratorRemove() {
        array.add("Alice");
        array.add("Bob");

        Iterator<String> iterator = array.iterator();
        assertEquals("Alice", iterator.next());
        iterator.remove();
        assertEquals(1, array.getSize());
        assertEquals("Bob", array.get(0));
    }

    @Test
    public void testRemoveBeyondCapacity() {
        DynamicArray<String> array = new DynamicArray<>(2);
        array.add("Alice");
        array.add("Bob");
        array.add("Charlie");

        array.remove(1);
        assertEquals(2, array.getSize());
        assertEquals("Alice", array.get(0));
        assertEquals("Charlie", array.get(1));
    }

    @Test
    public void testCapacityDoubling() {
        DynamicArray<String> array = new DynamicArray<>(1);
        array.add("Alice");
        array.add("Bob");
        array.add("Charlie"); // Ensure capacity expansion is working

        assertEquals(3, array.getSize());
        assertEquals("Charlie", array.get(2));
    }
}
