package com.thealgorithms.datastructures.heaps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class HeapElementTest {

    @Test
    void testConstructorAndGetters() {
        HeapElement element = new HeapElement(5.0, "Info");
        assertEquals(5.0, element.getKey());
        assertEquals("Info", element.getInfo());
    }

    @Test
    void testConstructorWithNullInfo() {
        HeapElement element = new HeapElement(10);
        assertEquals(10, element.getKey());
        assertNull(element.getInfo());
    }

    @Test
    void testToString() {
        HeapElement element = new HeapElement(7.5, "TestInfo");
        assertEquals("Key: 7.5 - TestInfo", element.toString());

        HeapElement elementWithoutInfo = new HeapElement(3);
        assertEquals("Key: 3.0 - No additional info", elementWithoutInfo.toString());
    }

    @Test
    void testEquals() {
        HeapElement element1 = new HeapElement(2.5, "Data");
        HeapElement element2 = new HeapElement(2.5, "Data");
        HeapElement element3 = new HeapElement(3.0, "DifferentData");

        assertEquals(element1, element2); // Same key and info
        assertNotEquals(element1, element3); // Different key
        assertNotEquals(null, element1); // Check for null
        assertNotEquals("String", element1); // Check for different type
    }

    @Test
    void testHashCode() {
        HeapElement element1 = new HeapElement(4, "HashMe");
        HeapElement element2 = new HeapElement(4, "HashMe");
        HeapElement element3 = new HeapElement(4, "DifferentHash");

        assertEquals(element1.hashCode(), element2.hashCode()); // Same key and info
        assertNotEquals(element1.hashCode(), element3.hashCode()); // Different info
    }
}
